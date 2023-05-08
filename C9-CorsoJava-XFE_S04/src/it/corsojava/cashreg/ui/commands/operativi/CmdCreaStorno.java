package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.core.exceptions.ScontrinoCreateException;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.intermedi.CmdModoRegistratore;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

import java.util.HashMap;
import java.util.Map;

public class CmdCreaStorno extends TerminalUiCommand {

    public CmdCreaStorno(TerminalUiAgent agent) {
        super(agent);
        this.label="Crea storno";
    }

    @Override
    public void run() throws UiCommandException {
        try {
            Scontrino s = this.agent.getRegistratore().creaStorno();
            boolean goAhead=true;
            while(goAhead) {
                Map<Integer,TerminalUiCommand> options = new HashMap<Integer,TerminalUiCommand>();
                options.put(1,new CmdScontrinoAggiungiRigaManuale(this.agent));
                options.put(2,new CmdStornoImportaDaScontrino(this.agent));
                options.put(3,new CmdScontrinoVisualizzaRighe(this.agent));
                options.put(4,new CmdScontrinoTerminaEStampa(this.agent));

                int input = getUserCommand(options);
                options.get(input).run();
                if(input==4){
                    goAhead=false;
                }
            }
        } catch (ScontrinoCreateException e) {
            getUi().writeln("Si e' verificato un problema nella creazione dello storno: "+e.getMessage());
        }
        CmdModoRegistratore modReg=new CmdModoRegistratore(this.agent);
        modReg.run();
    }
}
