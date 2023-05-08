package it.corsojava.cashreg.core.exceptions;

public class RegistratoreLoadException extends RegistratoreException{
    public RegistratoreLoadException() {
    }

    public RegistratoreLoadException(String message) {
        super(message);
    }

    public RegistratoreLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistratoreLoadException(Throwable cause) {
        super(cause);
    }

    public RegistratoreLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
