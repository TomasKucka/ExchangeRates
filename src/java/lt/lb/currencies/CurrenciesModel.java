package lt.lb.currencies;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CurrenciesModel {
    
    private final String currenciesListUrl = "https://www.lb.lt/webservices/ExchangeRates/ExchangeRates.asmx/getListOfCurrencies";

    public List<CurrenciesWrapper> processRequest() throws Exception {
        List<CurrenciesWrapper> list;
        list = parseXml();

        Collections.sort(list, new Comparator<CurrenciesWrapper>() {
            @Override
            public int compare(CurrenciesWrapper o1, CurrenciesWrapper o2) {
                return o1.getDescription().compareTo(o2.getDescription());
            }
        });

        return list;
    }

    private List<CurrenciesWrapper> parseXml() throws Exception {
        try {
            URL url = new URL(currenciesListUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(in);

            doc.getDocumentElement().normalize();

            List<CurrenciesWrapper> list = new ArrayList<>();
            
            NodeList nList = doc.getElementsByTagName("item");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    CurrenciesWrapper currency = new CurrenciesWrapper();
                    Element eElement = (Element) nNode;
                    
                    currency.setCurrency(eElement.getElementsByTagName("currency").item(0).getTextContent());
                    currency.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                    
                    list.add(currency);
                }
            }
            
            return list;
        } catch (IOException | ParserConfigurationException | SAXException | DOMException e) {
            throw new Exception("Coud not get currency list !");
        }
    }

}
