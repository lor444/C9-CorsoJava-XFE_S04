package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdVenditeGeneraliTotaleComplessivo extends TerminalUiCommand {


    public CmdVenditeGeneraliTotaleComplessivo(TerminalUiAgent agent) {
        super(agent);
        this.label="Totale complessivo vendite";
    }

    @Override
    public void run() throws UiCommandException {
        getUi().writeln("Totale complessivo vendite: "+this.agent.getRegistratore().getVenditeGeneraliTotaleComplessivo());
    }
}
