package app.commands.simulation.keyboard;

import app.Main;
import app.commands.Command;
import app.commands.Script;
import app.script.ScriptController;
import app.script.ScriptRunner;
import app.script.ValuesContainer;

/**
 * Created by G06 on 27/11/2015.
 */
public class KeyHold implements Command {
    private int key;

    @Override
    public void run( ) {
        Main.robot.delay(5);
        Main.robot.keyPress(key);
    }

    @Override
    public void setValues(ValuesContainer valueContainer) {
        key = KEYCODE.get(Integer.parseInt(valueContainer.get(1)));
        if(key == -1)
            throw new Script.InvalidCommandException("Mouse button code " + valueContainer.get(1)+ " does not exist");
    }
}
