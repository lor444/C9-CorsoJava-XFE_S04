package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Riga;
import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.core.datatypes.base.PercOutOfRangeException;
import it.corsojava.cashreg.core.datatypes.specifici.Iva;
import it.corsojava.cashreg.core.datatypes.specifici.Sconto;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdScontrinoAggiungiRigaManuale extends TerminalUiCommand {

    public CmdScontrinoAggiungiRigaManuale(TerminalUiAgent agent) {
        super(agent);
        this.label="Aggiungi riga manualmente";
    }

    @Override
    public void run() throws UiCommandException {
        Scontrino currentScontrino = this.agent.getRegistratore().getCurrentScontrino();
        if(currentScontrino==null){
            getUi().writeln("Nessuno scontrino corrente aperto. Creare un nuovo scontrino per potervi aggiungere righe");
        }else {
            Riga r = currentScontrino.creaNuovaRiga();
            r.setPrezzoUnitario(this.agent.getUi().askDouble("Importo unitario"));
            r.setQuantita(this.agent.getUi().askInt("Quantita"));
            Sconto sconto = null;
            while (sconto == null) {
                try {
                    sconto = new Sconto(this.agent.getUi().askDouble("Sconto"));
                } catch (PercOutOfRangeException ex) {
                    this.agent.getUi().writeln("Il valore percentuale deve essere un numero compreso tra -100 e +100");
                }
            }
            r.setSconto(sconto);
            this.agent.getUi().write("Selezionare l'aliquota: A = 22%, B = 10%, C=4%, D=nessuna >");
            String s = this.agent.getUi().read();
            switch (s) {
                case "A":
                    r.setIva(Iva.IVA_22);
                    break;
                case "B":
                    r.setIva(Iva.IVA_10);
                    break;
                case "C":
                    r.setIva(Iva.IVA_4);
                    break;
                default:
                    r.setIva(Iva.IVA_0);
            }
        }
    }
}
