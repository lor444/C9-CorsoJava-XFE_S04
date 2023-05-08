package it.corsojava.cashreg.core.implementation.exceptions;

public class StoreEngineLoadRigaException extends StoreEngineLoadScontrinoException{
    public StoreEngineLoadRigaException() {
    }

    public StoreEngineLoadRigaException(String message) {
        super(message);
    }

    public StoreEngineLoadRigaException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreEngineLoadRigaException(Throwable cause) {
        super(cause);
    }

    public StoreEngineLoadRigaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
