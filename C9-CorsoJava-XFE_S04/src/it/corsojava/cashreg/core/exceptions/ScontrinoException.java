package it.corsojava.cashreg.core.exceptions;

public class ScontrinoException extends  Exception{

    public ScontrinoException() {
    }

    public ScontrinoException(String message) {
        super(message);
    }

    public ScontrinoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScontrinoException(Throwable cause) {
        super(cause);
    }

    public ScontrinoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
