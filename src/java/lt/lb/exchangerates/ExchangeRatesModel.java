package lt.lb.exchangerates;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import lt.lb.webservices.exchangerates.ExchangeRates;
import lt.lb.webservices.exchangerates.ExchangeRatesSoap;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ExchangeRatesModel {

    private final String dateFormat = "yyyy.MM.dd";
    private final DateFormat df = new SimpleDateFormat(dateFormat);
    private final String maxDate = "2014.12.31";

    public List<ExchangeRatesWrapper> processRequest(String exchangeRatesDate) throws Exception {
        try {
            validateDate(exchangeRatesDate);
            
            String currentDateXmlString = getExchangeRatesByDateXmlString(exchangeRatesDate);
            List<ExchangeRatesWrapper> currentDateList = parseXml(currentDateXmlString);
            
            Date currentDate = df.parse(exchangeRatesDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            cal.add(Calendar.DATE, -1);
            String previousDate = df.format(cal.getTime());
            
            String prevoiousDateXmlString = getExchangeRatesByDateXmlString(previousDate);
            List<ExchangeRatesWrapper> previousDatelist = parseXml(prevoiousDateXmlString);
            
            for (int i = 0; i < currentDateList.size(); i++) {
                BigDecimal change = (currentDateList.get(i).getRate().subtract(previousDatelist.get(i).getRate()))
                        .divide(previousDatelist.get(i).getRate(), 5, RoundingMode.HALF_UP)
                        .multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
                currentDateList.get(i).setChange(change);
                currentDateList.get(i).setPreviousRate(previousDatelist.get(i).getRate());
            }

            Collections.sort(currentDateList, Collections.reverseOrder(new Comparator<ExchangeRatesWrapper>() {
                @Override
                public int compare(ExchangeRatesWrapper o1, ExchangeRatesWrapper o2) {
                    return o1.getChange().compareTo(o2.getChange());
                }
            }));
            
            return currentDateList;
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    private void validateDate(String exchangeRatesDate) throws Exception {
        if (exchangeRatesDate.isEmpty()) {
            throw new Exception("Please choose date !");
        }
        
        try {
            Date date = df.parse(exchangeRatesDate);
            if (date.after(df.parse(maxDate))) {
                throw new Exception("Date should not be greater then " + maxDate);
            }
        } catch (ParseException e) {
            throw new Exception("Could not validate date. Use format " + dateFormat + " !");
        }
    }
    
    private static String getExchangeRatesByDateXmlString(String date) {
        ExchangeRates service = new ExchangeRates();
        ExchangeRatesSoap port = service.getExchangeRatesSoap();

        return port.getExchangeRatesByDateXmlString(date);
    }
    
    private List<ExchangeRatesWrapper> parseXml(String xmlString) throws Exception {
        try {
            List<ExchangeRatesWrapper> list = new ArrayList<>();
            
            DOMParser parser = new DOMParser();
            parser.parse(new InputSource(new StringReader(xmlString)));
            Document doc = parser.getDocument();

            NodeList items = doc.getElementsByTagName("item");
            int itemsCount = items.getLength();
            for (int i = 0; i < itemsCount; i++) {
                String currency = doc.getElementsByTagName("currency").item(i).getTextContent();
                String quantity = doc.getElementsByTagName("quantity").item(i).getTextContent();
                BigDecimal rate = new BigDecimal(doc.getElementsByTagName("rate").item(i).getTextContent());
                String unit = doc.getElementsByTagName("unit").item(i).getTextContent();

                ExchangeRatesWrapper exchangeRates = new ExchangeRatesWrapper(currency, quantity, unit, rate, null, null);
                list.add(exchangeRates);
            }
            
            return list;
        } catch (SAXException | IOException ex) {
            throw new Exception("Coud not parse xml, wrong parameters !");
        }
    }

}
