package it.corsojava.cashreg.ui;

import it.corsojava.cashreg.core.*;
import it.corsojava.cashreg.ui.commands.intermedi.CmdIngresso;

public class TerminalUiAgent {

    private RegistratoreScontrini registratore;
    private TerminalUi ui;
    private ScontrinoPrinter printer;

    public TerminalUiAgent(RegistratoreScontrini registratore){
        this.registratore=registratore;
    }

    public void run() throws UiAgentStartupException{
        if(this.ui==null){
            throw new UiAgentStartupNullUiException();
        }
        if(this.registratore==null){
            throw new UiAgentStartupNullRegisterException("Nessun RegistratoreScontrini e' stato agganciato. Impossibile avviare l'agente");
        }
        try {
            CmdIngresso cmdIngresso = new CmdIngresso(this);
            cmdIngresso.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void setPrinter(ScontrinoPrinter printer){
        this.printer=printer;
    }

    public void setTerminalUi(TerminalUi ui){
        this.ui=ui;
    }
    public TerminalUi getUi(){
        return this.ui;
    }

    public RegistratoreScontrini getRegistratore(){
        return this.registratore;
    }
    public void setRegistratoreScontrini(RegistratoreScontrini registratore){
        this.registratore=registratore;
    }

    public void stampaScontrino(Scontrino s){
        getUi().writeln(this.printer.stampaScontrino(s));
    }

}
