package pl.kurs.jg.exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationTest {
    Map<String, Map<String, CurrenciesCouple>> currencyData = new HashMap<>();
    String currencySymbol1 = "USD";
    String currencySymbol2 = "EUR";
    Map<String, CurrenciesCouple> ratesForCurrency1 = new HashMap<>();
    Map<String, CurrenciesCouple> ratesForCurrency2 = new HashMap<>();
    Configuration configuration = new Configuration(currencyData);
    Double averageRate = 0.7564;
    Double spread = averageRate * 0.1;
    Double reversedAverageRate = 0.5 * (1 / (averageRate + 0.5 * spread) + 1 / (averageRate - 0.5 * spread));
    Double reversedSpread = 1 / (averageRate - 0.5 * spread) - 1 / (averageRate + 0.5 * spread);

    @Before
    public void init() {
        currencyData.clear();
        ratesForCurrency1.clear();
        ratesForCurrency2.clear();
    }


    @Test
    public void shouldSaveAverageRateAndSpreadForTwoCurrencies() {
        // given
        currencyData.put(currencySymbol1, ratesForCurrency1);
        currencyData.put(currencySymbol2, ratesForCurrency2);

        // when
        configuration.registerCurrenciesCouple(currencySymbol1, currencySymbol2, averageRate, spread);


        // then
        Assert.assertEquals(averageRate, currencyData.get(currencySymbol1).get(currencySymbol2).getAverageRate());
        Assert.assertEquals(spread, currencyData.get(currencySymbol1).get(currencySymbol2).getSpread());
    }

    @Test
    public void shouldAddTwoCurrenciesWithAverageRateAndSpread() {
        // given

        // when
        configuration.registerCurrenciesCouple(currencySymbol1, currencySymbol2, averageRate, spread);


        // then
        Assert.assertEquals(averageRate, currencyData.get(currencySymbol1).get(currencySymbol2).getAverageRate());
        Assert.assertEquals(spread, currencyData.get(currencySymbol1).get(currencySymbol2).getSpread());
    }

    @Test
    public void shouldSaveReversedAverageRateAndSpreadForTwoCurrencies() {
        // given
        currencyData.put(currencySymbol1, ratesForCurrency1);
        currencyData.put(currencySymbol2, ratesForCurrency2);

        // when
        configuration.registerCurrenciesCouple(currencySymbol1, currencySymbol2, averageRate, spread);

        // then
        Assert.assertEquals(reversedAverageRate, currencyData.get(currencySymbol2).get(currencySymbol1).getAverageRate(), 0.00001);
        Assert.assertEquals(reversedSpread, currencyData.get(currencySymbol2).get(currencySymbol1).getSpread(), 0.00001);

    }

    @Test
    public void shouldAddTwoCurrenciesWithReversedAverageRateAndSpread() {
        // given

        // when
        configuration.registerCurrenciesCouple(currencySymbol1, currencySymbol2, averageRate, spread);

        // then
        Assert.assertEquals(reversedAverageRate, currencyData.get(currencySymbol2).get(currencySymbol1).getAverageRate(), 0.00001);
        Assert.assertEquals(reversedSpread, currencyData.get(currencySymbol2).get(currencySymbol1).getSpread(), 0.00001);
    }
}
