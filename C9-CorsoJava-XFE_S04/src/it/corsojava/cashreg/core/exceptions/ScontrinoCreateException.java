package it.corsojava.cashreg.core.exceptions;

public class ScontrinoCreateException extends  ScontrinoException{

    public ScontrinoCreateException() {
    }

    public ScontrinoCreateException(String message) {
        super(message);
    }

    public ScontrinoCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScontrinoCreateException(Throwable cause) {
        super(cause);
    }

    public ScontrinoCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
