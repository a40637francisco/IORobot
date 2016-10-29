package app.commands;


import app.javafxUI.MainWindow;
import app.script.ScriptController;
import app.script.ScriptReader;
import app.script.ScriptRunner;
import app.script.ValuesContainer;

public class Script implements Command {

    private String scriptName;

    @Override
    public void run() {
        ScriptReader reader = new ScriptReader();
        reader.run(MainWindow.BASE_DIR + scriptName);
        System.out.println("Read script:" + scriptName);
    }

    @Override
    public void setValues(ValuesContainer valueContainer) {
        scriptName = valueContainer.get(0).replace("@","");

        if(scriptName == null) throw new InvalidCommandException("wrong command args");
    }

    public static class InvalidCommandException extends RuntimeException {
        public InvalidCommandException(String s) {
            System.out.printf(s);
        }
    }
}
