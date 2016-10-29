package app.javafxUI;

import app.Main;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.util.Optional;


public class MainWindow extends BorderPane {

    public final LeftSide leftSide;
    public final RightSide rightSide;
    private final WebEngine webEngine;

    public interface Publisher {
        void publish(double x, double y);
    }

    public static final int BUTTON_SPACING = 20;
    public static final String textFont = "Helvetica";

    private double bt_text_size = 17;

    public static final String BASE_DIR = "/scripts/";


    public MainWindow() {
        super();

        this.setWidth(Main.WIDTH);
        this.setHeight(Main.HEIGHT);

        leftSide = new LeftSide();
        rightSide = new RightSide();

        leftSide.setConnection(rightSide);
        rightSide.setConnection(leftSide);
        WebView webCrims = new WebView();
        webEngine = webCrims.getEngine();
        webEngine.load("https://www.google.com");


        this.setLeft(leftSide);
        this.setCenter(webCrims);
        this.setRight(rightSide);
        //this.setDividerPositions(0.5);
        //this.setOrientation(Orientation.HORIZONTAL);
        //this.getItems().add(0, leftSide);
        //this.getItems().add(1, webCrims);
        //this.getItems().add(2, rightSide);

    }

    public class RightSide extends VBox {

        public static final String RIGHT_TEXT_FONT = "Helvetica";

        private final DisplaySection displaySection;
        private final InputSection inputSection;

        private LeftSide connection;

        public RightSide() {
            super();
            displaySection = new DisplaySection();
            inputSection = new InputSection();

            displaySection.setConnection(inputSection);
            inputSection.setConnection(displaySection);

            this.getChildren().add(displaySection);
            this.getChildren().add(inputSection);
        }

        public void setConnection(LeftSide connection) {
            this.connection = connection;
        }

        public void updateScriptNameTf(String name){
            inputSection.setScriptNameTf(name);
        }

        public void updateScriptArea(String s){
            inputSection.updateLowerPane(s);
        }

    }


    public class LeftSide extends VBox implements Publisher {

        private String label_bt_create = "Create";
        private String label_bt_credits = "Credits";
        private Button bt_MouseCoords;
        private Text text_mouseCoords;


        private RightSide connection;

        public LeftSide() {
            super();
            this.setSpacing(BUTTON_SPACING);
            this.setAlignment(Pos.TOP_CENTER);
            this.getChildren().add(createCreateButton());
            this.getChildren().add(createCheckMouseCoordsButton());
            this.getChildren().add(urlTextField());
            this.getChildren().add(createCreditsButton());
        }

        private Node urlTextField() {
            TextField tf = new TextField();
            tf.setOnKeyReleased(event -> {
                if (event.getCode() == KeyCode.ENTER){
                    if(!tf.getText().equals("")) { // check regex valid url
                        webEngine.load(tf.getText());
                    }
                }
            });

            return tf;
        }

        private Node createCheckMouseCoordsButton() { //ficar as coordenadas escritas, parar quando clicar tecla ou ficar com rato parado?
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.TOP_CENTER);

            bt_MouseCoords = new Button("Mouse x/y");
            bt_MouseCoords.setFont(new Font(textFont, bt_text_size));
            bt_MouseCoords.setOnMouseClicked(new EventHandler<MouseEvent>() {

                private boolean on = false;
                private MouseCoordsRunnable mc;

                @Override
                public void handle(MouseEvent event) {
                    if (!on) {
                        on = true;
                        mc = new MouseCoordsRunnable();
                        mc.setListenerText(text_mouseCoords);
                        new Thread(mc).start();

                    } else {
                        on = false;
                        mc.callStop();
                    }
                }
            });

            text_mouseCoords = new Text("...");
            text_mouseCoords.setFont(new Font(textFont, bt_text_size));
            hBox.getChildren().addAll(bt_MouseCoords, text_mouseCoords);
            return hBox;
        }


        @Override
        public void publish(double x, double y) {
            text_mouseCoords.setText("X:" + x + " Y:" + y);
        }

        public void setConnection(RightSide connection) {
            this.connection = connection;
        }

        private class MouseCoordsRunnable implements Runnable {
            private boolean isRunning = true;
            private double x = -1, y = -1;
            private Text listenerText;


            public void callStop() {
                text_mouseCoords.setText("...");
                isRunning = false;
            }

            public void setListenerText(Text listenerText) {
                this.listenerText = listenerText;
            }


            @Override
            public void run() {
                while (isRunning) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Point p = MouseInfo.getPointerInfo().getLocation();
                            if (x != p.getX() || y != p.getY()) {
                                x = p.getX() - Main.activeStage.getX();
                                y = p.getY() - Main.activeStage.getY();
                                listenerText.setText(x + " " + y);
                            }
                        }
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
            }
        }


        private Node createCreditsButton() {
            Button bt_Credits = new Button(label_bt_credits);
            bt_Credits.setFont(new Font(textFont, bt_text_size));
            bt_Credits.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Alert.create()
                            .title("Credits")
                            .message("Created by Franmcod")
                            .show();

                }
            });
            return bt_Credits;
        }

        private Node createCreateButton() { //todo aler for result nome do ficheiro, limpar caixas
            Button bt_Create = new Button(label_bt_create);
            bt_Create.setFont(new Font(textFont, bt_text_size));
            bt_Create.setOnAction(event -> {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Script Name Dialog");
                dialog.setHeaderText("Enter a script name");
                dialog.setContentText("Script name:");

                Optional<String> result = dialog.showAndWait();
                // The Java 8 way to get the response value (with lambda expression).
                result.ifPresent(name -> {
                    connection.updateScriptNameTf(name);
                    connection.updateScriptArea("");
                });

            });
            return bt_Create;
        }

    }


}
