package it.corsojava.cashreg.ui;

import java.time.format.DateTimeFormatter;

public interface Formats {

    public DateTimeFormatter getDateFormat();
    public DateTimeFormatter getTimeFormat();
    public DateTimeFormatter getDateTimeFormat();

}
