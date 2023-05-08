package it.corsojava.cashreg.ui.commands.intermedi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdTorna extends TerminalUiCommand {

    public CmdTorna(TerminalUiAgent agent) {

        super(agent);
        this.label="Torna indietro";
    }

    @Override
    public void run() throws UiCommandException {
        /* Non fa nulla. Solo segnaposto */
    }
}
