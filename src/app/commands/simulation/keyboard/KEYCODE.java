package app.commands.simulation.keyboard;

import java.awt.event.KeyEvent;

/**
 * Created by G06 on 25/11/2015.
 */
public enum KEYCODE {
    CTRL(KeyEvent.VK_CONTROL),
    SHIFT(KeyEvent.VK_SHIFT)
    ;


    private final int code;

    KEYCODE(int code) {
        this.code = code;
    }

    public int valueOf() {
        return code;
    }

    public static int get(int i) {
        for(KEYCODE m : values())
            if(m.code == i)
                return m.code;
        return -1;
    }
}

