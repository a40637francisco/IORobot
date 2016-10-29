package app.commands.simulation.mouse;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * Created by G06 on 26/11/2015.
 */
public enum MOUSECODE {
    LEFT(MouseEvent.BUTTON1_MASK),
    RIGHT(MouseEvent.BUTTON3_MASK)

    ;


    private final int bt;

    MOUSECODE(int button1DownMask) {
        this.bt = button1DownMask;
    }

    public static int get(int i) {
        for(MOUSECODE m : values())
            if(m.ordinal() == i)
                return m.bt;
        return -1;
    }
}
