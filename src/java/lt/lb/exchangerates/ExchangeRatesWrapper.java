package lt.lb.exchangerates;

import java.math.BigDecimal;

public class ExchangeRatesWrapper {

    private String currency;
    private String quantity;
    private String unit;
    private BigDecimal rate;
    private BigDecimal previousRate;
    private BigDecimal change;

    public ExchangeRatesWrapper() {
    }
    
    public ExchangeRatesWrapper(String currency, String quantity, String unit, BigDecimal rate, BigDecimal previousRate, BigDecimal change) {
        this.currency = currency;
        this.quantity = quantity;
        this.unit = unit;
        this.rate = rate;
        this.previousRate = previousRate;
        this.change = change;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getPreviousRate() {
        return previousRate;
    }

    public void setPreviousRate(BigDecimal previousRate) {
        this.previousRate = previousRate;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

}
