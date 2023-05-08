package it.corsojava.cashreg.core.implementation.exceptions;

public class StoreEngineException extends Exception{
    public StoreEngineException() {
    }

    public StoreEngineException(String message) {
        super(message);
    }

    public StoreEngineException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreEngineException(Throwable cause) {
        super(cause);
    }

    public StoreEngineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
