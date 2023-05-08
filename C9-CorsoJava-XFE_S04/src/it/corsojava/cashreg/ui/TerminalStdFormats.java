package it.corsojava.cashreg.ui;

import java.time.format.DateTimeFormatter;

public class TerminalStdFormats implements Formats {

    @Override
    public DateTimeFormatter getDateFormat() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    @Override
    public DateTimeFormatter getTimeFormat() {
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    @Override
    public DateTimeFormatter getDateTimeFormat() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    }

}
