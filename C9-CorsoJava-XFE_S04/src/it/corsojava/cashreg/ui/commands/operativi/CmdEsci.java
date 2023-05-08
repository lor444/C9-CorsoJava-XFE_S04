package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;

public class CmdEsci extends TerminalUiCommand {


    public CmdEsci(TerminalUiAgent agent) {
        super(agent);
        this.label="Esci";
    }

    @Override
    public void run() {
        System.exit(0);
    }

}
