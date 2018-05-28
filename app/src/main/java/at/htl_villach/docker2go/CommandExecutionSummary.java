package at.htl_villach.docker2go;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kroel on 17.05.2018.
 */

public class CommandExecutionSummary {

    private List<Command> commands;
    private List<Exception> exceptions;

    public CommandExecutionSummary(){
        this.commands = new ArrayList<>();
        this.exceptions = new ArrayList<>();
    }

    public List<Exception> getExceptions() {
        return exceptions;
    }

    public boolean exececutedWithExceptions(){
        return exceptions.size() != 0;
    }

    public boolean allCommandsSuccessful() {
        for(Command command : commands) {
            if(!command.exitedAsExpected())
                return false;
        }

        return true;
    }

    public void addCommand(Command c){
        this.commands.add(c);
    }

    public void addException(Exception e){
        this.exceptions.add(e);
    }

    public Exception getLatestException(){
        return this.exceptions.get(this.exceptions.size() - 1);
    }
}
