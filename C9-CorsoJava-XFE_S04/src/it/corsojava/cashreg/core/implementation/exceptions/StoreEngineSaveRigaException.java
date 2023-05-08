package it.corsojava.cashreg.core.implementation.exceptions;

public class StoreEngineSaveRigaException extends StoreEngineSaveScontrinoException{

    public StoreEngineSaveRigaException() {
    }

    public StoreEngineSaveRigaException(String message) {
        super(message);
    }

    public StoreEngineSaveRigaException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreEngineSaveRigaException(Throwable cause) {
        super(cause);
    }

    public StoreEngineSaveRigaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
