package pl.kurs.jg.exchange;

public class CurrenciesCouple implements Cloneable {
    private String currency;
    private String referenceCurrency;
    private Double averageRate;
    private Double spread;
    private Double sellingRate;
    private Double buyingRate;

    CurrenciesCouple(String currency, String referenceCurrency) {
        this.currency = currency;
        this.referenceCurrency = referenceCurrency;
    }

    @Override
    public CurrenciesCouple clone() {
        Object copy = null;

        try {
            copy = super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }

        return (CurrenciesCouple) copy;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
        adjustRates();
    }

    public Double getSpread() {
        return spread;
    }

    public void setSpread(Double spread) {
        this.spread = spread;
        adjustRates();
    }

    public String getCurrency() {
        return currency;
    }

    public String getReferenceCurrency() {
        return referenceCurrency;
    }

    public Double getSellingRate() {
        return sellingRate;
    }

    public Double getBuyingRate() {
        return buyingRate;
    }

    private void adjustRates() {
        if (averageRate != null && spread != null) {
            sellingRate = Utils.calculateSellingRate(averageRate, spread);
            buyingRate = Utils.calculateBuyingRate(averageRate, spread);
        }
    }
}
