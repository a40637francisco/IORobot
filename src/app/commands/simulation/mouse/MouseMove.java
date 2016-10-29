package app.commands.simulation.mouse;


import app.Main;
import app.commands.Command;
import app.commands.Script;
import app.script.ScriptController;
import app.script.ScriptRunner;
import app.script.ValuesContainer;

public class MouseMove implements Command {


    private int x, y;

    @Override
    public void run() {
        Main.robot.delay(5);
        Main.robot.mouseMove(x, y);
    }

    @Override
    public void setValues(ValuesContainer valueContainer) {
        String[] split = valueContainer.get(1).split(":");
        x = (int) (Integer.parseInt(split[0]) + Main.activeStage.getX());
        y = (int) (Integer.parseInt(split[1]) + Main.activeStage.getY());
        if(x == -1 || y == -1)
            throw new Script.InvalidCommandException(""); //TODO
    }


}
