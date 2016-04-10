package lt.lb.chart;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lt.lb.webservices.exchangerates.ExchangeRates;
import lt.lb.webservices.exchangerates.ExchangeRatesSoap;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ChartModel {

    private final String dateFormat = "yyyy.MM.dd";
    private final DateFormat df = new SimpleDateFormat(dateFormat);
    private final String maxLowDate = "2014.12.31";

    public List<ChartWrapper> processRequest(String currency, String exchangeRatesDateLow, String exchangeRatesDateHigh) throws Exception {
        try {
            validateDates(exchangeRatesDateLow, exchangeRatesDateHigh);
            String exchangeRatesByCurrencyxmlString = getExchangeRatesByCurrencyXmlString(currency, exchangeRatesDateLow, exchangeRatesDateHigh);
            List<ChartWrapper> list = parseXml(exchangeRatesByCurrencyxmlString);

            return list;
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
    }

    private void validateDates(String exchangeRatesDateLow, String exchangeRatesDateHigh) throws Exception {
        if (exchangeRatesDateLow.isEmpty()) {
            throw new Exception("Please choose date FROM !");
        }
        
        Date dateLow;
        try {
            dateLow = df.parse(exchangeRatesDateLow);
            if (dateLow.after(df.parse(maxLowDate))) {
                throw new Exception("Date FROM should not be greater then " + maxLowDate);
            }
        } catch (ParseException e) {
            throw new Exception("Could not validate date FROM. Use format " + dateFormat + " !");
        }
        
        Date dateHigh = null;
        try {
            if (!exchangeRatesDateHigh.isEmpty()) {
                dateHigh = df.parse(exchangeRatesDateHigh);
            }
        } catch (ParseException e) {
            throw new Exception("Could not validate date TO. Use format " + dateFormat + " !");
        }
        
        if (dateHigh != null && dateLow.after(dateHigh)) {
            throw new Exception("Date FROM should not be greater then date TO !");
        }
    }

    private static String getExchangeRatesByCurrencyXmlString(String currency, String exchangeRatesDateLow, String exchangeRatesDateHigh) {
        ExchangeRates service = new ExchangeRates();
        ExchangeRatesSoap port = service.getExchangeRatesSoap();

        return port.getExchangeRatesByCurrencyXmlString(currency, exchangeRatesDateLow, exchangeRatesDateHigh);
    }

    private List<ChartWrapper> parseXml(String xmlString) throws Exception {
        try {
            List<ChartWrapper> list = new ArrayList<>();

            DOMParser parser = new DOMParser();
            parser.parse(new InputSource(new StringReader(xmlString)));
            Document doc = parser.getDocument();

            NodeList items = doc.getElementsByTagName("item");
            int itemsCount = items.getLength();
            for (int i = 0; i < itemsCount; i++) {
                String date = doc.getElementsByTagName("date").item(i).getTextContent();
                String rate = doc.getElementsByTagName("rate").item(i).getTextContent();

                ChartWrapper chart = new ChartWrapper(date, rate);
                list.add(chart);
            }

            return list;
        } catch (SAXException | IOException e) {
            throw new Exception("Coud not parse xml, wrong parameters !");
        }
    }

}
