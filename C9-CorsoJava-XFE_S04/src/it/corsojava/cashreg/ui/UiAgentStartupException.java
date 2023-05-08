package it.corsojava.cashreg.ui;

public class UiAgentStartupException extends  UiAgentException{

    public UiAgentStartupException() {
    }

    public UiAgentStartupException(String message) {
        super(message);
    }

    public UiAgentStartupException(String message, Throwable cause) {
        super(message, cause);
    }

    public UiAgentStartupException(Throwable cause) {
        super(cause);
    }

    public UiAgentStartupException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
