package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.time.LocalDate;

public class CmdVenditeGiornaliereTotale extends TerminalUiCommand {


    public CmdVenditeGiornaliereTotale(TerminalUiAgent agent) {
        super(agent);
        this.label="Totale vendite giornaliere";
    }

    @Override
    public void run() throws UiCommandException {
        double totale=this.agent.getRegistratore().getVenditeGiornaliereTotale(LocalDate.now());
        getUi().writeln("Totale delle vendite di giornata: "+totale);
    }
}
