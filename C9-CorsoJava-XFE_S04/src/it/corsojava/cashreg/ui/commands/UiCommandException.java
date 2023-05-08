package it.corsojava.cashreg.ui.commands;

public class UiCommandException extends Exception {
    public UiCommandException() {
    }

    public UiCommandException(String message) {
        super(message);
    }

    public UiCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public UiCommandException(Throwable cause) {
        super(cause);
    }

    public UiCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
