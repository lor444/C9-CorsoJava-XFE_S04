package it.corsojava.cashreg.core.implementation.exceptions;

public class StoreEngineInitException extends  StoreEngineException{

    public StoreEngineInitException() {
    }

    public StoreEngineInitException(String message) {
        super(message);
    }

    public StoreEngineInitException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreEngineInitException(Throwable cause) {
        super(cause);
    }

    public StoreEngineInitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
