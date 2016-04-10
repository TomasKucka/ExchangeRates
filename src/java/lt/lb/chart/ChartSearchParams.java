package lt.lb.chart;

public class ChartSearchParams {
    
    private String currency;
    private String exchangeRatesDateLow;
    private String exchangeRatesDateHigh;

    public ChartSearchParams() {
    }

    public ChartSearchParams(String currency, String exchangeRatesDateLow, String exchangeRatesDateHigh) {
        this.currency = currency;
        this.exchangeRatesDateLow = exchangeRatesDateLow;
        this.exchangeRatesDateHigh = exchangeRatesDateHigh;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchangeRatesDateLow() {
        return exchangeRatesDateLow;
    }

    public void setExchangeRatesDateLow(String exchangeRatesDateLow) {
        this.exchangeRatesDateLow = exchangeRatesDateLow;
    }

    public String getExchangeRatesDateHigh() {
        return exchangeRatesDateHigh;
    }

    public void setExchangeRatesDateHigh(String exchangeRatesDateHigh) {
        this.exchangeRatesDateHigh = exchangeRatesDateHigh;
    }
    
}
