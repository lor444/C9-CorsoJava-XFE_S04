package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CmdVenditeGiornaliereElencoScontrini extends TerminalUiCommand {


    public CmdVenditeGiornaliereElencoScontrini(TerminalUiAgent agent) {
        super(agent);
        this.label="Elenco scontrini di giornata";
    }

    public void printScontrinoHead(Scontrino s){
        StringBuilder sb=new StringBuilder();
        if(s!=null) {
            sb.append(s.getId());
            sb.append(" ");
            sb.append(s.getOra().format(DateTimeFormatter.ofPattern("HH:mm")));
            sb.append(" ");
            sb.append(s.getTipo());
            sb.append(switch (s.getTipo()) {
                case VENDITA -> "    ";
                case STORNO -> "     ";
                case CHIUSURA -> "   ";
                default -> " ";
            });
            sb.append(s.getTotaleComplessivo());
        }
        getUi().writeln(sb.toString());
    }

    @Override
    public void run() throws UiCommandException {
        List<Scontrino> elenco = this.agent.getRegistratore().getVenditeGiornaliereElencoScontrini(LocalDate.now());
        long nrScontrini=0;
        if(elenco!=null) {
             nrScontrini = elenco
                    .stream()
                    .sorted((s0, s1) -> s0.getOra().compareTo(s1.getOra()))
                    .peek(this::printScontrinoHead)
                    .collect(Collectors.toList()).size();
        }
        getUi().writeln("Sono stati ritrovati " + nrScontrini + " movimenti");
    }
}
