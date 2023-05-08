package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.exceptions.StornoImportException;
import it.corsojava.cashreg.core.TipiScontrino;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdStornoImportaDaScontrino extends TerminalUiCommand {

    public CmdStornoImportaDaScontrino(TerminalUiAgent agent) {
        super(agent);
        this.label="Importa righe da scontrino";
    }

    @Override
    public void run() throws UiCommandException {
        String id = getUi().askStirng("Digitare l'id dello scontrino da stornare: ");
        this.agent.getRegistratore()
            .findScontrino(id)
            .filter(s -> s.getTipo()==TipiScontrino.VENDITA)
            .ifPresentOrElse(
                s -> {
                    try {
                        this.agent.getRegistratore().popolaStornoDaScontrino(s);
                    } catch (StornoImportException e) {
                        getUi().writeln("Si e' verificato un problema nella generazione automatica dello storno da scontrino: "+e.getMessage()+". Impossibile procedere");
                    }
                }, () -> getUi().writeln("Nessuno scontrino corrisponde all'id dello scontrino da stornare")
            );
    }
}
