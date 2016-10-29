package app.script;

import app.commands.Command;
import app.commands.Script;
import app.commands.Wait;
import app.commands.multiLine.CurlyBrakets;
import app.commands.multiLine.Repeat;
import app.commands.simulation.keyboard.KeyClick;
import app.commands.simulation.keyboard.KeyHold;
import app.commands.simulation.keyboard.KeyRelease;
import app.commands.simulation.mouse.MouseClick;
import app.commands.simulation.mouse.MouseHold;
import app.commands.simulation.mouse.MouseMove;
import app.commands.simulation.mouse.MouseRelease;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ScriptController {

    private static final int COMMAND_POS = 0;



    public interface Worker{
        Command create();
    }

    private static final Map<String, Worker> commands = new HashMap<>();


    static {
        commands.put(CommandsKeys.MOUSE_CLICK.valueOf(), MouseClick::new);
        commands.put(CommandsKeys.MOUSE_HOLD.valueOf(), ()-> new MouseHold());
        commands.put(CommandsKeys.MOUSE_RELEASE.valueOf(), ()-> new MouseRelease());
        commands.put(CommandsKeys.MOUSE_MOVE.valueOf(), ()-> new MouseMove());
        commands.put(CommandsKeys.KEY_CLICK.valueOf(),()->  new KeyClick());
        commands.put(CommandsKeys.KEY_HOLD.valueOf(), ()-> new KeyHold());
        commands.put(CommandsKeys.KEY_RELEASE.valueOf(), ()-> new KeyRelease());
        commands.put(CommandsKeys.WAIT.valueOf(), ()-> new Wait());
        commands.put(CommandsKeys.SCRIPT.valueOf(),()->  new Script());
        commands.put(CommandsKeys.REPEAT.valueOf(), ()-> new Repeat());
        commands.put(CommandsKeys.End_Multi_Line.valueOf(), ()-> new CurlyBrakets());
    }

    public static Command getCommand(String[] split) {
        String commandKey = split[COMMAND_POS];
        if(commandKey.equals("")) {
            throw new RuntimeException("Script has empty lines!\nFailed to run");
        }
        Command c = getCommand(commandKey);
        if(c == null){
            System.out.println("key that failled: " + commandKey);
            throw new Script.InvalidCommandException("command does not exist \n");
        }

        c.setValues(createValueContainer(split));
        return c;
    }


    private static Command getCommand(String wantedKey) {
        Command destinyMap = null;

        for (Map.Entry<String, Worker> entry : commands.entrySet()) {
            String key = entry.getKey();
            Pattern p = Pattern.compile(key);
            Matcher m = p.matcher(wantedKey);
            if (m.find()) {
                destinyMap = entry.getValue().create();
                break;
            }
        }
        if (destinyMap == null) System.out.println("No Matching Command"); //TODO
        return destinyMap;
    }


    private static ValuesContainer createValueContainer(String[] c) {
        ValuesContainer v = new ValuesContainer();
        v.setValues(c);
        return v;
    }


}
