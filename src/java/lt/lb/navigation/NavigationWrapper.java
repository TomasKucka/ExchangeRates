package lt.lb.navigation;

public class NavigationWrapper {
    
    private String exchangeRatesPageButton;
    private String currencyChartPageButton;

    public NavigationWrapper() {
    }

    public NavigationWrapper(String exchangeRatesPageButton, String currencyChartPageButton) {
        this.exchangeRatesPageButton = exchangeRatesPageButton;
        this.currencyChartPageButton = currencyChartPageButton;
    }

    public String getExchangeRatesPageButton() {
        return exchangeRatesPageButton;
    }

    public void setExchangeRatesPageButton(String exchangeRatesPageButton) {
        this.exchangeRatesPageButton = exchangeRatesPageButton;
    }

    public String getCurrencyChartPageButton() {
        return currencyChartPageButton;
    }

    public void setCurrencyChartPageButton(String currencyChartPageButton) {
        this.currencyChartPageButton = currencyChartPageButton;
    }
    
}
