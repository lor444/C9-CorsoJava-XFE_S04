package it.corsojava.cashreg.core.exceptions;

public class RegistratoreException extends Exception{
    public RegistratoreException() {
    }

    public RegistratoreException(String message) {
        super(message);
    }

    public RegistratoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistratoreException(Throwable cause) {
        super(cause);
    }

    public RegistratoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
