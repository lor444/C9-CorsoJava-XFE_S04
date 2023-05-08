package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Articolo;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.util.List;

public class CmdVenditeGeneraliArchivioArticoli extends TerminalUiCommand {


    public CmdVenditeGeneraliArchivioArticoli(TerminalUiAgent agent) {
        super(agent);
        this.label="Archivio articoli";
    }

    @Override
    public void run() throws UiCommandException {
        List<Articolo> elenco =this.agent.getRegistratore().getArticoli();
        if(elenco!=null) {
            elenco.stream()
                    .forEach(a -> {
                        StringBuilder sb = new StringBuilder();
                        sb.append(a.getCodArticolo());
                        sb.append("  ");
                        sb.append(a.getBarcode());
                        sb.append("  ");
                        sb.append(a.getAliquotaIva());
                        sb.append("  ");
                        sb.append(a.getPrezzoUnitario());
                        sb.append("  ");
                        sb.append(a.getDenominazione());
                        if (a.getDescrizione() != null && a.getDescrizione().length() > 0) {
                            sb.append(" - ");
                            sb.append(a.getDescrizione());
                        }
                        getUi().writeln(sb.toString());
                    });
        }
    }

}
