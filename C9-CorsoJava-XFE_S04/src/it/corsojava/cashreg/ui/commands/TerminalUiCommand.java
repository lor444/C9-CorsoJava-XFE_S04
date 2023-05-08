package it.corsojava.cashreg.ui.commands;

import it.corsojava.cashreg.ui.TerminalUi;
import it.corsojava.cashreg.ui.TerminalUiAgent;

import java.util.Map;

public abstract class TerminalUiCommand {

    protected TerminalUiAgent agent;
    protected String label;

    public TerminalUiCommand(TerminalUiAgent agent){
        this.agent=agent;
    }

    public String getLabel(){
        return this.label;
    }

    public void setLabel(String label){
        this.label=label;
    }

    protected int getUserCommand(Map<Integer,TerminalUiCommand> options) throws UiCommandException{
        try {
            if (options == null)
                throw new UiCommandMapException("Null commands map");

            StringBuilder sb = new StringBuilder();
            sb.append(label);
            sb.append("> ");
            try {
                options.keySet().stream().forEach(k -> {
                    sb.append("[").append(k).append("] ").append(options.get(k).getLabel()).append(" ");
                });
            } catch (Exception ex) {
                throw new UiCommandMapException();
            }
            sb.append(":");
            this.agent.getUi().write(sb.toString());
            String input = this.agent.getUi().read();
            int cmd=-1;
            try {
                cmd= Integer.parseInt(input);
            }catch(Exception ex){
                this.agent.getUi().writeln("Comando non valido");
                return getUserCommand(options);
            }
            if (options.containsKey(cmd)) {
                return cmd;
            }else {
                this.agent.getUi().writeln("Comando non riconosciuto");
                return getUserCommand(options);
            }
        }catch(Exception ex){
            throw new UiCommandException(ex);
        }
    }

    public TerminalUi getUi() {
        return this.agent.getUi();
    }

    public abstract void run() throws UiCommandException;

}
