package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.exceptions.ScontrinoSearchException;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.time.LocalDate;
import java.util.Map;

public class CmdVenditeGeneraliTotaliGiornalieri extends TerminalUiCommand {


    public CmdVenditeGeneraliTotaliGiornalieri(TerminalUiAgent agent) {
        super(agent);
        this.label="Elenco totali giornalieri";
    }

    @Override
    public void run() throws UiCommandException {
        try {
            LocalDate dataInizio=LocalDate.of(2000,1,1);
            LocalDate dataFine=LocalDate.of(2099,12,31);
            Map<LocalDate,Double> totali= agent.getRegistratore().getVenditeGeneraliTotaliGiornalieri(dataInizio,dataFine);
            if(totali!=null) {
                totali.keySet().stream()
                        .sorted((t1, t2) -> t1.compareTo(t2))
                        .map(t -> t + " " + totali.get(t))
                        .forEach(System.out::println);
            }else{
                getUi().writeln("Non ci sono dati per visualizzare i totali per giornata");
            }
        } catch (ScontrinoSearchException e) {
            getUi().writeln("Si e' verificato un problema nella ricerca dei totali per giornata: "+e.getMessage());
        }
    }
}
