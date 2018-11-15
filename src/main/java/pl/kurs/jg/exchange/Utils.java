package pl.kurs.jg.exchange;

import java.math.BigDecimal;

public class Utils {
    public static Double calculateSellingRate(Double averageRate, Double spread) {

        if (averageRate == null || spread == null) {
            return null;
        }

        return averageRate + 0.5 * spread;
    }

    public static Double calculateBuyingRate(Double averageRate, Double spread) {

        if (averageRate == null || spread == null) {
            return null;
        }

        return averageRate - 0.5 * spread;
    }

    public static Double calculateReversedSellingRate(Double averageRate, Double spread) {

        Double buyingRate = calculateBuyingRate(averageRate, spread);

        if (buyingRate == null || buyingRate == 0) {
            return null;
        }


        return 1 / buyingRate;
    }

    public static Double calculateReversedBuyingRate(Double averageRate, Double spread) {

        Double sellingRate = calculateSellingRate(averageRate, spread);

        if (sellingRate == null || sellingRate == 0) {
            return null;
        }

        return 1 / sellingRate;
    }

    public static Double calculateReversedAverageRate(Double averageRate, Double spread) {

        Double reversedBuyingRate = calculateReversedBuyingRate(averageRate, spread);
        Double reversedSellingRate = calculateReversedSellingRate(averageRate, spread);

        if (reversedBuyingRate == null || reversedSellingRate == null) {
            return null;
        }

        return 0.5 * (reversedBuyingRate + reversedSellingRate);
    }

    public static Double calculateReversedSpread(Double averageRate, Double spread) {
        if (averageRate == null || spread == null) {
            return null;
        }

        Double reversedBuyingRate = calculateReversedBuyingRate(averageRate, spread);
        Double reversedSellingRate = calculateReversedSellingRate(averageRate, spread);


        return reversedSellingRate - reversedBuyingRate;
    }

    public static Double round(Double value) {
        return new BigDecimal(value)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }
}
