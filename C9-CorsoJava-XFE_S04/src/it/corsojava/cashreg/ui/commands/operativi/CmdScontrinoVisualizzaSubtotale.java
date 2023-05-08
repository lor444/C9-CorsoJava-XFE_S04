package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdScontrinoVisualizzaSubtotale extends TerminalUiCommand {


    public CmdScontrinoVisualizzaSubtotale(TerminalUiAgent agent) {
        super(agent);
        this.label="Visualizza subtotale";
    }

    @Override
    public void run() throws UiCommandException {
        Scontrino scontrino = this.agent.getRegistratore().getCurrentScontrino();
        if(scontrino!=null) {
            getUi().writeln("Totale parziale: " + scontrino.getTotaleComplessivo());
        }else{
            getUi().writeln("Non c'e' alcuno scontrino in corso di editazione");
        }
    }
}
