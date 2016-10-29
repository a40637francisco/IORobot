package app.commands.multiLine;

import app.commands.Command;
import app.commands.multiLine.MultiCommand;
import app.script.ScriptRunner;
import app.script.ValuesContainer;

/**
 * Created by G06 on 30/11/2015.
 */
public class Repeat implements Command, MultiCommand {

    private int times;

    ScriptRunner runner;

    public Repeat() {
        runner = new ScriptRunner();
    }

    @Override
    public void run() {
        int i = times;
        while (i-- > 0) {
            runner.loopThroughCommands();
        }

    }

    @Override
    public void setValues(ValuesContainer valueContainer) {
        times = Integer.parseInt(valueContainer.get(1));
    }


    @Override
    public ScriptRunner getRunner() {
        return runner;
    }
}
