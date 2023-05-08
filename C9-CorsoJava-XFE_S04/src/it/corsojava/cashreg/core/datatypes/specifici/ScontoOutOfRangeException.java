package it.corsojava.cashreg.core.datatypes.specifici;

import it.corsojava.cashreg.core.datatypes.base.PercOutOfRangeException;

public class ScontoOutOfRangeException extends PercOutOfRangeException {

    public ScontoOutOfRangeException() {
    }

    public ScontoOutOfRangeException(String message) {
        super(message);
    }

    public ScontoOutOfRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScontoOutOfRangeException(Throwable cause) {
        super(cause);
    }

    public ScontoOutOfRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
