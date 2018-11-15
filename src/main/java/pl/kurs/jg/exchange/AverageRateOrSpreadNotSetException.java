package pl.kurs.jg.exchange;

public class AverageRateOrSpreadNotSetException extends RuntimeException {

    AverageRateOrSpreadNotSetException(String currency, String referenceCurrency) {
        super(String.format("Dla pary walut \"%s\" i \"%s\" nie określono spreadu lub kursu referencyjnego", currency, referenceCurrency));
    }
}
