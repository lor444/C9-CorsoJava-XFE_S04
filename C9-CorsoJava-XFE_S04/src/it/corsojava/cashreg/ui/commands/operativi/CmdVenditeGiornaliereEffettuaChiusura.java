package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.exceptions.ChiusuraDiCassaException;
import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdVenditeGiornaliereEffettuaChiusura extends TerminalUiCommand {


    public CmdVenditeGiornaliereEffettuaChiusura(TerminalUiAgent agent) {
        super(agent);
        this.label="Effettua chiusura";
    }

    @Override
    public void run() throws UiCommandException {
        try {
            Scontrino chiusura=this.agent.getRegistratore().venditeGiornaliereEffettuaChiusura();
            this.agent.stampaScontrino(chiusura);

        } catch (ChiusuraDiCassaException e) {
            getUi().writeln("Si e' verificato un problema durante la registrazione delle operaizoni di chiusura: "+e.getMessage());
        }

    }
}
