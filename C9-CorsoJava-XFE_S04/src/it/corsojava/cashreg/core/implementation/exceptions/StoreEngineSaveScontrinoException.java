package it.corsojava.cashreg.core.implementation.exceptions;

public class StoreEngineSaveScontrinoException extends StoreEngineException{

    public StoreEngineSaveScontrinoException() {
    }

    public StoreEngineSaveScontrinoException(String message) {
        super(message);
    }

    public StoreEngineSaveScontrinoException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreEngineSaveScontrinoException(Throwable cause) {
        super(cause);
    }

    public StoreEngineSaveScontrinoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
