package it.corsojava.cashreg.ui;

public class UiAgentException extends Exception{

    public UiAgentException() {
    }

    public UiAgentException(String message) {
        super(message);
    }

    public UiAgentException(String message, Throwable cause) {
        super(message, cause);
    }

    public UiAgentException(Throwable cause) {
        super(cause);
    }

    public UiAgentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
