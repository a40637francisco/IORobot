package app.commands;


import app.commands.Script;
import app.script.ScriptController;
import app.script.ScriptRunner;
import app.script.ValuesContainer;

public class Wait implements Command {


    private long millis;

    @Override
    public void run() {
        try {
            Thread.sleep(millis);
            //System.out.println("slept " + millis + " millis");
        } catch (InterruptedException e) {
            System.out.println("cant sleep");
        }
    }

    @Override
    public void setValues(ValuesContainer valueContainer) {
        millis = Long.parseLong(valueContainer.get(1));
        millis *= 1000;
        if (millis == -1)
            throw new Script.InvalidCommandException("Mouse button code " + valueContainer.get(1) + " does not exist");
    }
}
