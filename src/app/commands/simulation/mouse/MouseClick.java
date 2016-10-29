package app.commands.simulation.mouse;

import app.Main;
import app.commands.Command;
import app.commands.Script;
import app.script.ScriptController;
import app.script.ScriptRunner;
import app.script.ValuesContainer;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by G06 on 25/11/2015.
 */
public class MouseClick implements Command {

    private int btToClick;


    @Override
    public void run() {
            Robot robot = Main.robot;
            robot.delay(5);
            robot.mousePress(btToClick);
            robot.delay(5);
            robot.mouseRelease(btToClick);
            System.out.println("clicked + " + btToClick);
    }

    @Override
    public void setValues(ValuesContainer valueContainer) { //values container vem mal?
        btToClick = MOUSECODE.get(Integer.parseInt(valueContainer.get(1)));
        if(btToClick == -1)
            throw new Script.InvalidCommandException("Mouse button code " + valueContainer.get(1)+ " does not exist");
    }


}
