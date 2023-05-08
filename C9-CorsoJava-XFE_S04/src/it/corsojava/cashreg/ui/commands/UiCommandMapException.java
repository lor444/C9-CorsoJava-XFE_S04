package it.corsojava.cashreg.ui.commands;

public class UiCommandMapException extends UiCommandException {

    public UiCommandMapException() {
    }

    public UiCommandMapException(String message) {
        super(message);
    }

    public UiCommandMapException(String message, Throwable cause) {
        super(message, cause);
    }

    public UiCommandMapException(Throwable cause) {
        super(cause);
    }

    public UiCommandMapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
