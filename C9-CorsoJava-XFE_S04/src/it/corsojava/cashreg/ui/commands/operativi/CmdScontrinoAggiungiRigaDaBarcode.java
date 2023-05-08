package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Riga;
import it.corsojava.cashreg.core.exceptions.ScontrinoAddRigaException;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdScontrinoAggiungiRigaDaBarcode extends TerminalUiCommand {


    public CmdScontrinoAggiungiRigaDaBarcode(TerminalUiAgent agent) {
        super(agent);
        this.label="Aggiungi riga da barcode";
    }

    @Override
    public void run() throws UiCommandException {
        String barcode = this.agent.getUi().askStirng("Inserire il barcode del prodotto: ");
        try {
            Riga r = this.agent.getRegistratore().addRigaToCurrentScontrinoByBarcode(barcode);
            if(r!=null){
                getUi().writeln("Riga aggiunta");
            }else{
                getUi().writeln("Prodotto non trovato");
            }
        } catch (ScontrinoAddRigaException e) {
            getUi().writeln("Si e' verificato un problema nell'aggiunta dello scontrino:"+e.getMessage());
        }

    }
}
