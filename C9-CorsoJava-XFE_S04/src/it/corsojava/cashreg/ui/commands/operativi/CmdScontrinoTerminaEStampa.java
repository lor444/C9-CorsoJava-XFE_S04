package it.corsojava.cashreg.ui.commands.operativi;

import it.corsojava.cashreg.core.RegistratoreScontrini;
import it.corsojava.cashreg.core.Scontrino;
import it.corsojava.cashreg.core.exceptions.ScontrinoException;
import it.corsojava.cashreg.ui.TerminalUiAgent;
import it.corsojava.cashreg.ui.commands.TerminalUiCommand;
import it.corsojava.cashreg.ui.commands.UiCommandException;

public class CmdScontrinoTerminaEStampa extends TerminalUiCommand {

    public CmdScontrinoTerminaEStampa(TerminalUiAgent agent) {
        super(agent);
        this.label="Termina e stampa";
    }

    @Override
    public void run() throws UiCommandException {
        if(this.agent == null) throw new UiCommandException("Il sevizio agente non e' disponibile per il comando di chiusura scontrino");
        RegistratoreScontrini registratore = this.agent.getRegistratore();
        if(registratore == null) throw new UiCommandException("Il servizio di registratore non e' correttamente impostato nel servizio agente");
        try {
            Scontrino scontrino = registratore.chiudiCurrentScontrino();
            if (scontrino != null){
                getUi().writeln("Scontrino registrato. Totale: " + scontrino.getTotaleComplessivo());
                agent.stampaScontrino(scontrino);
            }else getUi().writeln("La registrazione dello scontrino non e' stata possibile");
        } catch (ScontrinoException e) {
            getUi().writeln("Si e' verificato un problema nella registrazione dello scontrino: "+e.getMessage());
        }
    }
}
