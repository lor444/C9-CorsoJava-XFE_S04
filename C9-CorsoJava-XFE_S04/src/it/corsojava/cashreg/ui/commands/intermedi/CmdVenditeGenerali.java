package it.corsojava.cashreg.ui.commands.intermedi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;
import it.corsojava.cashreg.ui.commands.operativi.CmdVenditeGeneraliArchivioArticoli;
import it.corsojava.cashreg.ui.commands.operativi.CmdVenditeGeneraliTotaleComplessivo;
import it.corsojava.cashreg.ui.commands.operativi.CmdVenditeGeneraliTotaliGiornalieri;

import java.util.HashMap;
import java.util.Map;

public class CmdVenditeGenerali extends TerminalUiCommand {

    public CmdVenditeGenerali(TerminalUiAgent agent) {
        super(agent);
        this.label="Vendite generali";
    }

    @Override
    public void run() throws UiCommandException {
        boolean goAhead=true;
        while(goAhead) {
            Map<Integer,TerminalUiCommand> options = new HashMap<Integer,TerminalUiCommand>();
            options.put(0, new CmdTorna(this.agent)); // Crea scontrino
            options.put(1, new CmdVenditeGeneraliTotaliGiornalieri(this.agent)); // Per ogni giorno l'importo totale
            options.put(2, new CmdVenditeGeneraliTotaleComplessivo(this.agent)); // Totale cumulativo delle vendite per periodo
            options.put(3, new CmdVenditeGeneraliArchivioArticoli(this.agent)); // Totale cumulativo delle vendite per periodo


            int input = getUserCommand(options);
            options.get(input).run();
            if(input==0){
                goAhead=false;
            }
        }
        CmdModoRiepilogo modReg=new CmdModoRiepilogo(this.agent);
        modReg.run();
    }
}
