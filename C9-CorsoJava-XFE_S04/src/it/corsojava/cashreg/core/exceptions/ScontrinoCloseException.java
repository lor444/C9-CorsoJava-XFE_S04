package it.corsojava.cashreg.core.exceptions;

public class ScontrinoCloseException extends ScontrinoException{

    public ScontrinoCloseException() {
    }

    public ScontrinoCloseException(String message) {
        super(message);
    }

    public ScontrinoCloseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScontrinoCloseException(Throwable cause) {
        super(cause);
    }

    public ScontrinoCloseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
