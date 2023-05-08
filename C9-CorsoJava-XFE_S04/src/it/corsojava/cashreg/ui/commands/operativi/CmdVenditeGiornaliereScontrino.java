package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CmdVenditeGiornaliereScontrino extends TerminalUiCommand {


    public CmdVenditeGiornaliereScontrino(TerminalUiAgent agent) {
        super(agent);
        this.label="Visualizza scontrino";
    }

    @Override
    public void run() throws UiCommandException {
        String id = getUi().askStirng("Inserire l'id dello scontrino da visualizzare");
        List<Scontrino> elenco=this.agent.getRegistratore()
                .getVenditeGiornaliereElencoScontrini(LocalDate.now());
        if(elenco!=null) {
            Optional<Scontrino> scontrinoCercato = elenco
                    .stream()
                    .filter(s -> s.getId().equalsIgnoreCase(id))
                    .findFirst();
            if (scontrinoCercato.isPresent()) {
                this.agent.stampaScontrino(scontrinoCercato.get());
            } else {
                this.getUi().writeln("Nessuno scontrino nella giornata odierna corrisponde all'id indicato");
            }
        }else this.getUi().writeln("Nessuno scontrino nella giornata odierna corrisponde all'id indicato");
    }
}
