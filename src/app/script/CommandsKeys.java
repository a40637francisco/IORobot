package app.script;

/**
 * Created by G06 on 26/11/2015.
 */
public enum CommandsKeys {
    MOUSE_MOVE("^mouseMove$"),
    KEY_CLICK("^keyClick$"),
    KEY_HOLD("^keyHold$"),
    KEY_RELEASE("^keyRelease$"),
    MOUSE_CLICK("^mouseClick$"),
    MOUSE_HOLD("^mouseHold$"),
    MOUSE_RELEASE("^mouseRelease$"),
    WAIT("^wait$"),
    REPEAT("^repeat$"),
    SCRIPT("^@(.+?)$"),
    End_Multi_Line("^}"),
    ;


    CommandsKeys(String value) {
        this.value = value;
    }

    public String value;

    public String valueOf(){
        return value;
    }
}
