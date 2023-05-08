package it.corsojava.cashreg.ui.commands.intermedi;

import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;
import it.corsojava.cashreg.ui.commands.operativi.*;

import java.util.HashMap;
import java.util.Map;

public class CmdVenditeGiornaliere extends TerminalUiCommand {

    public CmdVenditeGiornaliere(TerminalUiAgent agent) {
        super(agent);
        this.label="Vendite giornaliere";
    }

    @Override
    public void run() throws UiCommandException {
        boolean goAhead=true;
        while(goAhead) {
            Map<Integer,TerminalUiCommand> options = new HashMap<Integer,TerminalUiCommand>();
            options.put(0, new CmdTorna(this.agent));
            options.put(1, new CmdVenditeGiornaliereEffettuaChiusura(this.agent)); // Registra scontrino di chiusura
            options.put(2, new CmdVenditeGiornaliereParziale(this.agent)); // Somma scontrini dall'ultimo di chiusura
            options.put(3, new CmdVenditeGiornaliereTotale(this.agent)); // Valore totale vendite giornaliere
            options.put(4, new CmdVenditeGiornaliereElencoScontrini(this.agent)); // List scontrini del giorno (chiusure comprese)
            options.put(5, new CmdVenditeGiornaliereScontrino(this.agent)); // Visualizza scontrino in base a id

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
