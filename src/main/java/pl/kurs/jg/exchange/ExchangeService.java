package pl.kurs.jg.exchange;

public class ExchangeService {
    private CurrenciesCouple currenciesCouple;
    private Double amountOfCurrency;

    ExchangeService(String currency, String referenceCurrency) {
        currenciesCouple = Configuration.getCurrenciesCouple(currency, referenceCurrency).clone();
    }

    public String getCurrency() {
        return currenciesCouple.getCurrency();
    }

    public String getReferenceCurrency() {
        return currenciesCouple.getReferenceCurrency();
    }

    public Double getAverageRate() {
        return currenciesCouple.getAverageRate();
    }

    public void setAverageRate(Double averageRate) {
        currenciesCouple.setAverageRate(averageRate);
    }

    public Double getSpread() {
        return currenciesCouple.getSpread();
    }

    public void setSpread(Double spread) {
        currenciesCouple.setSpread(spread);
    }

    public Double getBuyingRate() {
        return currenciesCouple.getBuyingRate();
    }

    public Double getSellingRate() {
        return currenciesCouple.getSellingRate();
    }

    // returns amount of reference currency to get from a customer
    public Double sell(Double amountOfCurrency) {
        Double sellingRate = getSellingRate();
        if (sellingRate == null) {
            throw new AverageRateOrSpreadNotSetException(getCurrency(), getReferenceCurrency());
        }

        return Utils.round(amountOfCurrency * sellingRate);
    }

    // returns amount of reference currency to pay to a customer
    public Double buy(Double amountOfCurrency) {

        Double buyingRate = getBuyingRate();

        if (buyingRate == null) {
            throw new AverageRateOrSpreadNotSetException(getCurrency(), getReferenceCurrency());
        }

        return Utils.round(amountOfCurrency * buyingRate);
    }

    // returns amount of currency to pay to a customer for given amount of reference currency
    public Double sellFor(Double amountOfReferenceCurrency) {

        Double sellingRate = getSellingRate();

        if (sellingRate == null) {
            throw new AverageRateOrSpreadNotSetException(getCurrency(), getReferenceCurrency());
        }

        return Utils.round(amountOfReferenceCurrency / sellingRate);
    }
}
