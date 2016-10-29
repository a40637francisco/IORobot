package app.commands.simulation.mouse;

import app.Main;
import app.commands.Command;
import app.commands.Script;
import app.script.ScriptController;
import app.script.ScriptRunner;
import app.script.ValuesContainer;

/**
 * Created by G06 on 26/11/2015.
 */
public class MouseHold implements Command {

    private int btToClick;

    @Override
    public void run( ) {
        Main.robot.delay(5);
        Main.robot.mousePress(btToClick);
    }

    @Override
    public void setValues(ValuesContainer valueContainer) {
        btToClick = MOUSECODE.get(Integer.parseInt(valueContainer.get(1)));
        if(btToClick == -1)
            throw new Script.InvalidCommandException("Mouse button code " + valueContainer.get(1)+ " does not exist");
    }
}
