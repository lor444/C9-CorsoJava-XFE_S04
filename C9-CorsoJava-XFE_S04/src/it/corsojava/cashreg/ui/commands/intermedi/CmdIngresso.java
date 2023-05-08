package it.corsojava.cashreg.ui.commands.intermedi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;
import it.corsojava.cashreg.ui.commands.operativi.CmdEsci;

import java.util.HashMap;
import java.util.Map;

public class CmdIngresso extends TerminalUiCommand {

    public CmdIngresso(TerminalUiAgent agent) {
        super(agent);
        this.label="Ingresso";
    }

    @Override
    public void run() throws UiCommandException {
        Map<Integer,TerminalUiCommand> options = new HashMap<Integer,TerminalUiCommand>();
        options.put(0,new CmdEsci(this.agent));
        options.put(1,new CmdSelezionaModo(this.agent));
        int input = getUserCommand(options);
        options.get(input).run();
    }

}
