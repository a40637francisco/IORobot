package app.javafxUI;



import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class Alert extends Stage{


    private static final double HEIGHT = 200;
    private static final double WIDTH = 400;
    private final AlertBoxLayout layout;
    private final String textFont = "Helvetica";
    private final double textSize = 18;
    private Font font;

    public Alert() {
        super();
        this.setResizable(false);
        this.initModality(Modality.APPLICATION_MODAL);
        font = new Font(textFont, textSize);
        this.setHeight(HEIGHT);
        this.setWidth(WIDTH);

        //addMedToUserStage.setX(WIDTH + addMedToUserStage.getHeight());
        //addMedToUserStage.setY(button.getLayoutY());
        layout = new AlertBoxLayout();
        Scene s = new Scene(layout, this.getWidth(), this.getHeight());
        this.setScene(s);
    }

    public static Alert create() {
        return new Alert();
    }

    public Alert title(String s) {
        this.setTitle(s);
        return this;
    }

    public Alert message(String s) {
        layout.setMessage(s);
        return this;
    }

    public Alert setFont(Font f){
        font = f;
        return this;
    }


    private class AlertBoxLayout extends Pane{

        private Text t;

        public AlertBoxLayout() {
            super();
            this.getChildren().add(createMessage());
        }

        private Node createMessage() {
            t = new Text();
            t.setFont(font);
            t.relocate( WIDTH/ 3 , HEIGHT / 2  - 30);
            return t;
        }

        public void setMessage(String m) {
            t.setText(m);
        }
    }

}
