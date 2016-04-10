package lt.lb.exchangerates;

public class ExchangeRatesSearchParams {
    
    private String exchangeRatesDate;

    public ExchangeRatesSearchParams() {
    }

    public ExchangeRatesSearchParams(String exchangeRatesDate) {
        this.exchangeRatesDate = exchangeRatesDate;
    }

    public String getExchangeRatesDate() {
        return exchangeRatesDate;
    }

    public void setExchangeRatesDate(String exchangeRatesDate) {
        this.exchangeRatesDate = exchangeRatesDate;
    }
    
}
