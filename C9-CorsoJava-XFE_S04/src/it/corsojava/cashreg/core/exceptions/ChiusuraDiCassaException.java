package it.corsojava.cashreg.core.exceptions;

public class ChiusuraDiCassaException extends  Exception{

    public ChiusuraDiCassaException() {
    }

    public ChiusuraDiCassaException(String message) {
        super(message);
    }

    public ChiusuraDiCassaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChiusuraDiCassaException(Throwable cause) {
        super(cause);
    }

    public ChiusuraDiCassaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
