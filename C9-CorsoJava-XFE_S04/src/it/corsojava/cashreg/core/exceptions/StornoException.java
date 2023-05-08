package it.corsojava.cashreg.core.exceptions;

public class StornoException extends  ScontrinoException{

    public StornoException() {
    }

    public StornoException(String message) {
        super(message);
    }

    public StornoException(String message, Throwable cause) {
        super(message, cause);
    }

    public StornoException(Throwable cause) {
        super(cause);
    }

    public StornoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
