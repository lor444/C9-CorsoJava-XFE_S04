package it.corsojava.cashreg.core.implementation.exceptions;

public class StoreEngineLoadScontrinoException extends StoreEngineLoadException{

    public StoreEngineLoadScontrinoException() {
    }

    public StoreEngineLoadScontrinoException(String message) {
        super(message);
    }

    public StoreEngineLoadScontrinoException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreEngineLoadScontrinoException(Throwable cause) {
        super(cause);
    }

    public StoreEngineLoadScontrinoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
