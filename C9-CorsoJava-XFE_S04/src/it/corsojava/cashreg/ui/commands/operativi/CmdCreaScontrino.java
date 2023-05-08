package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.core.exceptions.ScontrinoCreateException;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.intermedi.CmdModoRegistratore;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.util.HashMap;
import java.util.Map;

public class CmdCreaScontrino extends TerminalUiCommand {

    public CmdCreaScontrino(TerminalUiAgent agent) {
        super(agent);
        this.label="Crea scontrino";
    }

    @Override
    public void run() throws UiCommandException {
        try {
            Scontrino s = this.agent.getRegistratore().creaScontrinoVendita();

            boolean goAhead=true;
            while(goAhead) {
                Map<Integer,TerminalUiCommand> options = new HashMap<Integer,TerminalUiCommand>();
                options.put(1,new CmdScontrinoAggiungiRigaManuale(this.agent));
                options.put(2,new CmdScontrinoAggiungiRigaDaBarcode(this.agent));
                options.put(3,new CmdScontrinoSelezionaArticolo(this.agent));
                options.put(4,new CmdScontrinoVisualizzaSubtotale(this.agent));
                options.put(5,new CmdScontrinoVisualizzaRighe(this.agent));
                options.put(6,new CmdScontrinoTerminaEStampa(this.agent));

                int input = getUserCommand(options);
                options.get(input).run();
                if(input==6){
                    goAhead=false;
                }
            }
        } catch (ScontrinoCreateException e) {
            getUi().writeln("Si e' verificato un problema nella creazione dello scontrino: "+e.getMessage());
        }
        CmdModoRegistratore modReg=new CmdModoRegistratore(this.agent);
        modReg.run();
    }
}
