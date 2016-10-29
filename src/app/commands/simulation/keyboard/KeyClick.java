package app.commands.simulation.keyboard;

import app.Main;
import app.commands.Command;
import app.commands.Script;
import app.script.ScriptController;
import app.script.ScriptRunner;
import app.script.ValuesContainer;

/**
 * Created by G06 on 25/11/2015.
 */
public class KeyClick implements Command {


    private int key;

    @Override
    public void run() {
        Main.robot.delay(5);
        Main.robot.keyPress(key);
        Main.robot.delay(5);
        Main.robot.keyRelease(key);
    }

    @Override
    public void setValues(ValuesContainer valueContainer) {
        key = valueContainer.get(1).toUpperCase().charAt(0) + 0;
        if(key == -1)
            throw new Script.InvalidCommandException("Key button code " + valueContainer.get(1)+ " does not exist");
    }
}
