
package lt.lb.webservices.exchangerates;

import java.math.BigDecimal;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ExchangeRatesHttpPost", targetNamespace = "http://webservices.lb.lt/ExchangeRates")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ExchangeRatesHttpPost {


    /**
     * Returns a list of currencies.
     * 
     */
    @WebMethod
    public void getListOfCurrencies();

    /**
     * Returns the current exchange rate (expressed in Litas per 1 currency unit) for the specified currency.
     * 
     * @param currency
     * @return
     *     returns java.math.BigDecimal
     */
    @WebMethod
    @WebResult(name = "Body", partName = "Body")
    public BigDecimal getCurrentExchangeRate(
        @WebParam(name = "Currency", partName = "Currency")
        String currency);

    /**
     * Returns an exchange rate (expressed in Litas per 1 currency unit) for the specified currency and date.
     * 
     * @param date
     * @param currency
     * @return
     *     returns java.math.BigDecimal
     */
    @WebMethod
    @WebResult(name = "Body", partName = "Body")
    public BigDecimal getExchangeRate(
        @WebParam(name = "Currency", partName = "Currency")
        String currency,
        @WebParam(name = "Date", partName = "Date")
        String date);

    /**
     * Returns a XML formatted string containing exchange rates for the specified date.
     * 
     * @param date
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "getExchangeRatesByDate_XmlString")
    @WebResult(name = "Body", partName = "Body")
    public String getExchangeRatesByDateXmlString(
        @WebParam(name = "Date", partName = "Date")
        String date);

    /**
     * Returns a XML formatted string containing exchange rates for the specified currency that are between specified dates.
     * 
     * @param dateLow
     * @param dateHigh
     * @param currency
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "getExchangeRatesByCurrency_XmlString")
    @WebResult(name = "Body", partName = "Body")
    public String getExchangeRatesByCurrencyXmlString(
        @WebParam(name = "Currency", partName = "Currency")
        String currency,
        @WebParam(name = "DateLow", partName = "DateLow")
        String dateLow,
        @WebParam(name = "DateHigh", partName = "DateHigh")
        String dateHigh);

    /**
     * Returns a list containing exchange rates for the specified date.
     * 
     * @param date
     */
    @WebMethod
    public void getExchangeRatesByDate(
        @WebParam(name = "Date", partName = "Date")
        String date);

    /**
     * Returns a Xml Schema of provided Exchange Rates.
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "Body", partName = "Body")
    public String getExchangeRatesXmlSchema();

    /**
     * Returns a list containing exchange rates for the specified currency that are between specified dates.
     * 
     * @param dateLow
     * @param dateHigh
     * @param currency
     */
    @WebMethod
    public void getExchangeRatesByCurrency(
        @WebParam(name = "Currency", partName = "Currency")
        String currency,
        @WebParam(name = "DateLow", partName = "DateLow")
        String dateLow,
        @WebParam(name = "DateHigh", partName = "DateHigh")
        String dateHigh);

    /**
     * Returns a average exchange rate for the specified currency, between specified dates.
     * 
     * @param dateLow
     * @param dateHigh
     * @param currency
     */
    @WebMethod
    public void getExchangeAvgRate(
        @WebParam(name = "Currency", partName = "Currency")
        String currency,
        @WebParam(name = "DateLow", partName = "DateLow")
        String dateLow,
        @WebParam(name = "DateHigh", partName = "DateHigh")
        String dateHigh);

}
