package it.corsojava.cashreg;

import it.corsojava.cashreg.core.RegistratoreScontrini;
import it.corsojava.cashreg.core.exceptions.RegistratoreLoadException;
import it.corsojava.cashreg.core.implementation.IdGenerator;
import it.corsojava.cashreg.core.implementation.RegistratoreScontriniImpl;
import it.corsojava.cashreg.core.implementation.StoreEngine;
import it.corsojava.cashreg.core.implementation.engine.PropertiesStoreEngine;
import it.corsojava.cashreg.core.implementation.engine.SystemTimeIdGenerator;
import it.corsojava.cashreg.core.implementation.exceptions.StoreEngineException;
import it.corsojava.cashreg.core.implementation.exceptions.StoreEngineInitException;
import it.corsojava.cashreg.ui.*;

public class Main {
    public static void main(String[] args) {

        TerminalUi ui = new TerminalUi(new TerminalStdFormats(),System.in, System.out);
        ScontrinoPrinter printer=new ScontrinoBasicPrinter();

        // Va realizzata un'implmentazione concreta dell'interfaccia RegistratoreScontrini
        // che deve essere passata come argomento al costruttore di TerminalUiAgent, altrimenti
        // non verra' eseguito.
        // Dopo aver realizzato un'implmentazione concreta dell'interfaccia, crearne un'istanza
        // e assegnarla alla variabile "registratore". In questo modo l'agente potra' avviarsi

        IdGenerator gen = new SystemTimeIdGenerator();
        StoreEngine engine = null;
        try {
            engine = new PropertiesStoreEngine(gen);
        } catch (StoreEngineInitException e) {
            ui.writeln("Si e' verificato un problema durante l'avvio dei servizi del programma. Impossibile proseguire");
            e.printStackTrace();
            return;
        }

        RegistratoreScontrini registratore = null;
        try {
            registratore = new RegistratoreScontriniImpl(engine);
        } catch (StoreEngineException e) {
            ui.writeln("Si e' verificato un problema durante l'avvio dei servizi del programma. Impossibile proseguire");
            e.printStackTrace();
            return;
        } catch (RegistratoreLoadException e) {
            ui.writeln("Si e' verificato un problema durante l'avvio dei servizi del programma. Impossibile proseguire");
            e.printStackTrace();
            return;
        }

        TerminalUiAgent agent=new TerminalUiAgent(registratore);
        agent.setTerminalUi(ui);
        agent.setPrinter(printer);

        try {
            agent.run();
        } catch (UiAgentStartupException e) {
            ui.writeln("Si e' verificato un problema durante l'avvio dei servizi del programma. Impossibile proseguire");
            e.printStackTrace();
            return;
        }

    }
}
