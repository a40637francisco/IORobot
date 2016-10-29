package app.commands;

import app.script.ScriptRunner;
import app.script.ValuesContainer;

/**
 * Created on 28/10/2016.
 */
public interface Command {
    void run();
    void setValues(ValuesContainer valueContainer);
}
