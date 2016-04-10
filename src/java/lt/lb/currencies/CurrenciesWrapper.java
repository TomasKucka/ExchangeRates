package lt.lb.currencies;

public class CurrenciesWrapper {

    private String currency;
    private String description;

    public CurrenciesWrapper() {
    }

    public CurrenciesWrapper(String currency, String description) {
        this.currency = currency;
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
