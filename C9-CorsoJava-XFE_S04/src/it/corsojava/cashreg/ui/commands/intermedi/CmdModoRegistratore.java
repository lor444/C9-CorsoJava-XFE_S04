package it.corsojava.cashreg.ui.commands.intermedi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.operativi.CmdCreaStorno;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;
import it.corsojava.cashreg.ui.commands.operativi.CmdCreaScontrino;
import it.corsojava.cashreg.ui.commands.operativi.CmdEsci;

import java.util.HashMap;
import java.util.Map;

public class CmdModoRegistratore extends TerminalUiCommand {


    public CmdModoRegistratore(TerminalUiAgent agent) {
        super(agent);
        this.label="Modo Registratore";
    }

    @Override
    public void run() throws UiCommandException {
        Map<Integer,TerminalUiCommand> options = new HashMap<Integer,TerminalUiCommand>();
        options.put(0, new CmdEsci(this.agent));
        options.put(1, new CmdSelezionaModo("Cambia modo",this.agent)); // Cambia modo
        options.put(2, new CmdCreaScontrino(this.agent)); // Crea scontrino
        options.put(3, new CmdCreaStorno(this.agent)); // Crea storno


        int input = getUserCommand(options);
        options.get(input).run();
    }
}
