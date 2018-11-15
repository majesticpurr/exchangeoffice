package pl.kurs.jg.exchange;

import java.util.Set;

public class RateTable {
    private String referenceCurrency;


    public RateTable(String referenceCurrency) {
        this.referenceCurrency = referenceCurrency;
    }

    public void print() {
        System.out.println();
        System.out.println("Tablica kursowa dla waluty referencyjnej " + referenceCurrency + ":");
        System.out.println();

        Set<String> registeredCurrencies = Configuration.getRegisteredCurrencies();

        for (String currency : registeredCurrencies) {
            if (!referenceCurrency.equals(currency)) {
                CurrenciesCouple cc = Configuration.getCurrenciesCouple(currency, referenceCurrency);
                System.out.println(String.format("%.4f %s %.4f", cc.getBuyingRate(), cc.getCurrency(), cc.getSellingRate()));
            }
        }
    }
}
