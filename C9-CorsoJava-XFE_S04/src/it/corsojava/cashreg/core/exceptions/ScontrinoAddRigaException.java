package it.corsojava.cashreg.core.exceptions;

public class ScontrinoAddRigaException extends  ScontrinoException{

    public ScontrinoAddRigaException() {
    }

    public ScontrinoAddRigaException(String message) {
        super(message);
    }

    public ScontrinoAddRigaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScontrinoAddRigaException(Throwable cause) {
        super(cause);
    }

    public ScontrinoAddRigaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
