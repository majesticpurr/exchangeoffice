package pl.kurs.jg.exchange;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Configuration {
    private static Map<String, Map<String, CurrenciesCouple>> currencyData = new HashMap<>();


    Configuration() {
        super();
    }

    Configuration(Map<String, Map<String, CurrenciesCouple>> currencyData) {
        this();
        Configuration.currencyData = currencyData;
    }

    public static void registerCurrenciesCouple(String currency, String referenceCurrency, Double averageRate, Double spread) {
        registerCurrenciesCouple(currency, referenceCurrency, averageRate, spread, true);
    }

    private static void registerCurrenciesCouple(String currency, String referenceCurrency, Double averageRate, Double spread, boolean registerReversed) {

        CurrenciesCouple currenciesCouple = getCurrenciesCouple(currency, referenceCurrency);

        currenciesCouple.setAverageRate(averageRate);
        currenciesCouple.setSpread(spread);

        currencyData.putIfAbsent(currency, new HashMap<>());

        currencyData
                .get(currency)
                .put(referenceCurrency, currenciesCouple);

        if (registerReversed) {
            Double reversedAverageRate = Utils.calculateReversedAverageRate(averageRate, spread);
            Double reversedSpread = Utils.calculateReversedSpread(averageRate, spread);
            registerCurrenciesCouple(referenceCurrency, currency, reversedAverageRate, reversedSpread, false);
        }
    }

    public static CurrenciesCouple getCurrenciesCouple(String currency, String referenceCurrency) {

        return currencyData
                .getOrDefault(currency, new HashMap<>())
                .getOrDefault(referenceCurrency, new CurrenciesCouple(currency, referenceCurrency));
    }

    public static Set<String> getRegisteredCurrencies() {
        return new TreeSet<String>(currencyData.keySet());
    }

}
