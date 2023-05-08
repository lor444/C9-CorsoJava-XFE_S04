package it.corsojava.cashreg.core.implementation.exceptions;

public class StoreEngineLoadException extends StoreEngineException{

    public StoreEngineLoadException() {
    }

    public StoreEngineLoadException(String message) {
        super(message);
    }

    public StoreEngineLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreEngineLoadException(Throwable cause) {
        super(cause);
    }

    public StoreEngineLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
