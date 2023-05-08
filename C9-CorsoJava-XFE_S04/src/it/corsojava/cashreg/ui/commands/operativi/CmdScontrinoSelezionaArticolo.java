package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Articolo;
import it.corsojava.cashreg.core.Riga;
import it.corsojava.cashreg.core.exceptions.ScontrinoAddRigaException;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.util.HashMap;
import java.util.List;

public class CmdScontrinoSelezionaArticolo extends TerminalUiCommand {

    public CmdScontrinoSelezionaArticolo(TerminalUiAgent agent) {
        super(agent);
        this.label="Seleziona articolo";
    }

    @Override
    public void run() throws UiCommandException {
        HashMap<Integer, Articolo> map = new HashMap<Integer, Articolo>();
        Integer pos = 1;
        List<Articolo> articoli=this.agent.getRegistratore().getArticoli();
        if(articoli!=null) {
            for (Articolo a : articoli) {
                map.put(pos, a);
                this.agent.getUi().writeln("[" + pos + "] - " + a.getCodArticolo() + " - " + a.getDenominazione());
                pos++;
            }

            int choice = this.agent.getUi().askInt("Selezionare il numero corrispondente al prodotto: ");
            if (map.containsKey(choice)) {
                Articolo a = map.get(choice);
                if (a != null) {
                    try {
                        Riga r = this.agent.getRegistratore().addRigaToCurrentScontrino(a);
                        if (r != null) {
                            this.agent.getUi().writeln("Riga aggiunta correttamente");
                        } else {
                            this.agent.getUi().writeln("Riga NON aggiunta");
                        }
                    } catch (ScontrinoAddRigaException e) {
                        this.agent.getUi().writeln("Si e' verificato un problema nell'aggiunta della riga: " + e.getMessage());
                    }
                } else {
                    this.agent.getUi().writeln("Il prodotto selezionato non corrisponde ad alcun prodotto registrato");
                }
            } else {
                this.agent.getUi().writeln("Il valore immesso non corrisponde ad alcun valore presente nell'elenco");
            }
        }else this.agent.getUi().writeln("Non e' stato caricato l'archivio articoli");
    }
}
