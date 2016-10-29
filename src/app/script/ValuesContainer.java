package app.script;

/**
 * Created by G06 on 26/11/2015.
 */
public class ValuesContainer {

    private int x,y;

    private int times;

    private int time;

    private String[] values;

    public void setValues(String[] values) {
        this.values = values;
    }

    public String get(int i) {
        return values[i];
    }
}
