package it.corsojava.cashreg.ui.commands.intermedi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;
import it.corsojava.cashreg.ui.commands.operativi.CmdEsci;

import java.util.HashMap;
import java.util.Map;

public class CmdSelezionaModo extends TerminalUiCommand {


    public CmdSelezionaModo(String label,TerminalUiAgent agent){
        this(agent);
        this.label=label;
    }

    public CmdSelezionaModo(TerminalUiAgent agent) {
        super(agent);
        this.label="Seleziona modalita";
    }

    @Override
    public void run() throws UiCommandException {
        Map<Integer,TerminalUiCommand> options = new HashMap<Integer,TerminalUiCommand>();
        options.put(0, new CmdEsci(this.agent));
        options.put(1, new CmdModoRegistratore(this.agent));
        options.put(2, new CmdModoRiepilogo(this.agent));

        int input = getUserCommand(options);
        options.get(input).run();
    }

}
