package it.corsojava.cashreg.ui.commands.intermedi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;
import it.corsojava.cashreg.ui.commands.operativi.*;

import java.util.HashMap;
import java.util.Map;

public class CmdModoRiepilogo extends TerminalUiCommand {

    public CmdModoRiepilogo(TerminalUiAgent agent) {
        super(agent);
        this.label="Modo Riepilogo";
    }

    @Override
    public void run() throws UiCommandException {
        Map<Integer,TerminalUiCommand> options = new HashMap<Integer,TerminalUiCommand>();
        options.put(0, new CmdEsci(this.agent));
        options.put(1, new CmdSelezionaModo("Cambia modo",this.agent)); // Cambia modo
        options.put(2,new CmdVenditeGiornaliere(this.agent));
        options.put(3,new CmdVenditeGenerali(this.agent));


        int input = getUserCommand(options);
        options.get(input).run();
    }
}
