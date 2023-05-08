package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.exceptions.ScontrinoSearchException;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.time.LocalDate;
import java.util.Map;

public class CmdVenditeGeneraliTotaliGiornalieriPeriodo extends TerminalUiCommand {


    public CmdVenditeGeneraliTotaliGiornalieriPeriodo(TerminalUiAgent agent) {
        super(agent);
        this.label="Elenco totali giornalieri";
    }

    @Override
    public void run() throws UiCommandException {
        LocalDate dataInizio = getUi().askDate("Dal (compreso): ");
        LocalDate dataFine = getUi().askDate("Al (compreso): ");
        if (dataInizio.compareTo(dataFine)>0) {
            getUi().writeln("La data di inizio periodo di ricerca deve essere uguale o precedere la data di fine del periodo di ricerca.");
            getUi().writeln("Impossibile procedere");
        }else{
            try {
                Map<LocalDate,Double> totali= agent.getRegistratore().getVenditeGeneraliTotaliGiornalieri(dataInizio,dataFine);
                totali.keySet().stream()
                        .sorted( (t1,t2) -> t1.compareTo(t2))
                        .map(t -> t+" "+totali.get(t))
                        .forEach(System.out::println);
            } catch (ScontrinoSearchException e) {
                getUi().writeln("Si e' verificato un problema nella ricerca dei totali relativi alle giornate selezionate: "+e.getMessage());
            }
        }
    }
}
