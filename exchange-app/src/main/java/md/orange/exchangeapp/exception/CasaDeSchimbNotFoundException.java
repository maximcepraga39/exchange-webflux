package md.orange.exchangeapp.exception;

public class CasaDeSchimbNotFoundException extends RuntimeException {
    public CasaDeSchimbNotFoundException(String casaDeSchimb) {
        super("CasaDeSchimb not found: " + casaDeSchimb);
    }

    public CasaDeSchimbNotFoundException() {
        super("CasaDeSchimb not found");
    }
}
