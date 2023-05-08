package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdScontrinoVisualizzaRighe extends TerminalUiCommand {


    public CmdScontrinoVisualizzaRighe(TerminalUiAgent agent) {
        super(agent);
        this.label="Visualizza righe";
    }

    @Override
    public void run() throws UiCommandException {
        Scontrino scontrino = this.agent.getRegistratore().getCurrentScontrino();
        if(scontrino!=null) {
            scontrino.getRighe().stream().forEach(System.out::println);
        }else{
            getUi().writeln("Non c'e' alcuno scontrino in corso di editazione");
        }
    }
}
