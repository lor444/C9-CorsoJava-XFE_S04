package it.corsojava.cashreg.core.exceptions;

public class ScontrinoSearchException extends ScontrinoException{

    public ScontrinoSearchException() {
    }

    public ScontrinoSearchException(String message) {
        super(message);
    }

    public ScontrinoSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScontrinoSearchException(Throwable cause) {
        super(cause);
    }

    public ScontrinoSearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
