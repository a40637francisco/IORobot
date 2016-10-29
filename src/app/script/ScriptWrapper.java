package app.script;

import app.javafxUI.MainWindow;
import app.utils.MyFiles;

import java.util.List;

/**
 * Created by G06 on 25/11/2015.
 */
public class ScriptWrapper {

    private final ScriptReader scriptReader;
    private final ScriptWriter scriptWriter;

    public ScriptWrapper() {
        scriptReader = new ScriptReader();
        scriptWriter = new ScriptWriter();
    }

    public List<String> loadScripts(String s) {
        return scriptReader.getScriptNames(s);
    }

    public void addScript(String name, String data) {
        scriptWriter.writeFile(name, data);
    }

    public void deleteScript(String name){
        MyFiles.deleteFile(MyFiles.getCurrentDir() + MainWindow.BASE_DIR + name + ".txt");
    }


    public void runScript(String scriptName) {
        scriptReader.run(scriptName);
    }

    public String loadScript(String scriptName) {
        return scriptReader.getScriptCommandsTxt(scriptName);
    }
}
