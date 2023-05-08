package it.corsojava.cashreg.core.exceptions;

public class StornoImportException extends  StornoException{

    public StornoImportException() {
    }

    public StornoImportException(String message) {
        super(message);
    }

    public StornoImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public StornoImportException(Throwable cause) {
        super(cause);
    }

    public StornoImportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
