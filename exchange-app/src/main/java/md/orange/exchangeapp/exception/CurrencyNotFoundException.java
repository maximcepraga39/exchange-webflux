package md.orange.exchangeapp.exception;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String currencyCode) {
        super("Currency not found: " + currencyCode);
    }

    public CurrencyNotFoundException() {
        super("Currency not found");
    }
}
