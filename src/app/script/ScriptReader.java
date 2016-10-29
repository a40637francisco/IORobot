package app.script;

import app.commands.Command;
import app.commands.multiLine.MultiCommand;
import app.commands.multiLine.EndMultiLine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static app.script.ScriptController.*;
import static app.utils.MyFiles.getCurrentDir;

public class ScriptReader {

    private static String dirName;

    public static final String baseDir = "/scripts/";

    public ScriptReader() {
        dirName = "/scripts";
    }

    public List<String> getScriptNames(String s) {
        String currentPath = getCurrentDir() + s;
        try {
            List<String> r = new ArrayList<>();
            Files.walk(Paths.get(currentPath)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    r.add(filePath.getFileName().toString());
                }
            });
            return r;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    private LinkedList<ScriptRunner> runningQueue;
    public void run(String scriptName) {
        ScriptRunner runner = new ScriptRunner();
        runningQueue = new LinkedList<>();
        runningQueue.addFirst(runner);

        try(BufferedReader br = new BufferedReader(new FileReader(getCurrentDir()+ scriptName+".txt"))) {
            String line = br.readLine();
            while (line != null) {
                decodeLine(line);
                line = br.readLine();
            }
            runner.loopThroughCommands();
            System.out.println("Finished script:" + scriptName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void decodeLine(String line) {
        String[] split = line.split("/");
        Command c = getCommand(split);
        if(c instanceof MultiCommand) {
            MultiCommand m = (MultiCommand)c;
            runningQueue.getLast().addCommand(c);
            runningQueue.addLast(m.getRunner());
            return;
        }
        if(c instanceof EndMultiLine) {
            if(runningQueue.size() > 2)
                runningQueue.removeLast();
            return;
        }

        runningQueue.getLast().addCommand(c);

    }

    public String getScriptCommandsTxt(String scriptName) {
        try(BufferedReader br = new BufferedReader(new FileReader(getCurrentDir() + scriptName))) {
            String line = br.readLine();
            if(line == null) return "";
            StringBuilder sb = new StringBuilder();
            sb.append(line);
            sb.append('\n');
            while (line != null) {
                line = br.readLine();
                if(line == null) break;
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;//TODO throw exception
    }
}
