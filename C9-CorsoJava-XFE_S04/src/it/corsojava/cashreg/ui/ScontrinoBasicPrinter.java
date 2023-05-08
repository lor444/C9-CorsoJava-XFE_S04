package it.corsojava.cashreg.ui;

import it.corsojava.cashreg.core.Riga;
import it.corsojava.cashreg.core.Scontrino;

import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class ScontrinoBasicPrinter implements ScontrinoPrinter {

    private static final int COLUMNS=40;

    private String line;
    public ScontrinoBasicPrinter(){
        line =" "+String.join("", Collections.nCopies(COLUMNS+2,"-"))+" \n";
    }

    @Override
    public String stampaScontrino(Scontrino s) {
        StringBuilder sb=new StringBuilder();
        if(s!=null) {
            sb.append(line);
            sb.append(printRow(""));
            sb.append(printRow("Scontrino " + s.getId()));
            sb.append(printRow(""));
            sb.append(printRow(s.getTipo() + ""));
            sb.append(printRow(""));
            sb.append(printRow("Emesso il " + s.getData().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
            sb.append(printRow("Alle ore " + s.getOra().format(DateTimeFormatter.ofPattern("HH:mm"))));
            sb.append(printRow(""));
            for (Riga r : s.getRighe()) {
                sb.append(printRow(r.toString()));
                if (r.getDescrizione() != null && r.getDescrizione().length() > 0)
                    sb.append(printRow("   " + r.getDescrizione()));
            }
            sb.append(printRow(""));
            sb.append(line);
            sb.append(printRow("Totale: " + s.getTotaleComplessivo()));
            sb.append(line);
        }
        return sb.toString();
    }

    private String printRow(String content){
        StringBuilder sb=new StringBuilder();
        sb.append("| ");

        if(content.length()>COLUMNS){
            String a = content.substring(0,COLUMNS);
            String b= content.substring(COLUMNS);
            sb.append(a);
            sb.append(" |");
            sb.append("\n");
            sb.append(printRow(b));
        }else{
            sb.append(content);
            int spaces= COLUMNS-content.length();
            sb.append(String.join("",Collections.nCopies(spaces," ")));
            sb.append(" |");
            sb.append("\n");
        }
        return sb.toString();
    }

}
