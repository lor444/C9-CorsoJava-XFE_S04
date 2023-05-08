package it.corsojava.cashreg.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Scanner;

public class TerminalUi {

    private Formats formats;
    private Scanner in;
    private PrintStream out;

    public TerminalUi(Formats formats, InputStream in, PrintStream out) {
        this.formats = formats;
        this.in = new Scanner(System.in);
        this.out=out;
    }

    public void write(String str){
        out.print(str);
    }

    public void writeln(String str){
        out.println(str);
    }

    public String read(){
        return in.nextLine();
    }

    public String askStirng(String label){
        write(label);
        write(": ");
        return read();
    }

    public double askDouble(String label){
        String strNr=askStirng(label);
        double d = 0.0;
        try {
            d = Double.parseDouble(strNr);
        }catch(Exception ex){
            writeln("Formato numerico non valido: utilizzare il formato 123"+ DecimalFormatSymbols.getInstance().getDecimalSeparator()+"45");
            d = askDouble(label);
        }
        return d;
    }

    public int askInt(String label){
        String strNr=askStirng(label);
        int i = 0;
        try {
            i = Integer.parseInt(strNr);
        }catch(Exception ex){
            writeln("Il valore immesso non e' un numero valido");
            i = askInt(label);
        }
        return i;
    }

    public LocalDate askDate(String label){
        String strDate=askStirng(label);
        LocalDate ld = null;
        try {
            ld = LocalDate.parse(strDate,this.formats.getDateFormat());
        }catch(Exception ex){
            writeln("Formato datario non valido: utilizzare il formato "+this.formats.getDateFormat().toString());
            ld = askDate(label);
        }
        return ld;
    }

}
