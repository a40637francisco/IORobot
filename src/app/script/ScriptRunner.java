package app.script;

import app.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class ScriptRunner{

    public List<Command> RunningScript;

    public ScriptRunner() {
        RunningScript = new ArrayList<>();
    }

    public void addCommand(Command command) {
        RunningScript.add(command);
    }

    public void loopThroughCommands() {
        for(Command c : RunningScript) {
            c.run();
        }
    }
}
