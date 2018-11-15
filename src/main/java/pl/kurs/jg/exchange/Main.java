package pl.kurs.jg.exchange;


public class Main {
    public static void main(String[] args) {

        Configuration.registerCurrenciesCouple("EUR", "PLN", 4.2123, 0.2);

        ExchangeService es = new ExchangeService("EUR", "PLN");

        Double euroAmount = 124d;

        System.out.println("Za " + euroAmount + " " + es.getCurrency() + " klient musi zapłacić " + es.sell(euroAmount) + " " + es.getReferenceCurrency());
        System.out.println("Za " + euroAmount + " " + es.getCurrency() + " musisz dać klientowi " + es.buy(euroAmount) + " " + es.getReferenceCurrency());

        Configuration.registerCurrenciesCouple("USD", "PLN", 3.5213, 0.17);
        Double usdAmount = 10000d;

        es = new ExchangeService("USD", "PLN");
        System.out.println("Za " + usdAmount + " " + es.getCurrency() + " klient musi zapłacić " + es.sell(usdAmount) + " " + es.getReferenceCurrency());
        System.out.println("Za " + usdAmount + " " + es.getCurrency() + " musisz dać klientowi " + es.buy(usdAmount) + " " + es.getReferenceCurrency());

        // special discount for a very good customer - not persisted in configuration
        es.setAverageRate(es.getAverageRate() - 0.1);
        System.out.println("Po zniżce za " + usdAmount + " " + es.getCurrency() + " klient musi zapłacić " + es.sell(usdAmount) + " " + es.getReferenceCurrency());

        Configuration.registerCurrenciesCouple("USD", "EUR", 0.7821, 0.1);

        // printing rate tables
        RateTable rtPLN = new RateTable("PLN");
        rtPLN.print();

        RateTable rtEUR = new RateTable("EUR");
        rtEUR.print();
    }

}
