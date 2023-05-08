package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.time.LocalDateTime;

public class CmdVenditeGiornaliereParziale extends TerminalUiCommand {


    public CmdVenditeGiornaliereParziale(TerminalUiAgent agent) {
        super(agent);
        this.label="Parziale da ultima chiusura";
    }

    @Override
    public void run() throws UiCommandException {
        double parziale=this.agent.getRegistratore().getVenditeGiornaliereParziale(LocalDateTime.now());
        getUi().writeln("Parziale vendite dall'ultima chiusura (o da inizio giornata): "+parziale);
    }
}
