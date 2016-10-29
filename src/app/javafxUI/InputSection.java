package app.javafxUI;

import app.Main;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static app.javafxUI.MainWindow.RightSide.RIGHT_TEXT_FONT;

/**
 * Created on 18/10/2016.
 */
public class InputSection extends VBox {

    public static final String lowerPanelLabel = "Script Name: ";

    private ScrollPane lowerScrollPane;
    private VBox vBoxLowerHolder;

    private static TextField lowerLabelTextField;
    private static TextArea lowerText;


    private MenuButton commandMenuButton;


    public InputSection() {
        this.getChildren().addAll(
                header(),
                scriptArea(),
                buttons()
        );



    }

    /*+++++++++++++++++++++++++++++ HEADER +++++++++++++++++++++++++++++*/
    private Node header() {
        HBox hb = new HBox(
                label(lowerPanelLabel),
                scriptNameTf("")
        );

        return hb;
    }

    private Text label(String s) {
        Text txt = new Text(s);
        txt.setFont(new Font(RIGHT_TEXT_FONT, 18));
        return txt;
    }


    private TextField scriptNameTf(String name) {
        lowerLabelTextField = new TextField(name);
        lowerLabelTextField.setFont(new Font(RIGHT_TEXT_FONT, 18));
        return lowerLabelTextField;
    }

    public void setScriptNameTf(String s) {
        lowerLabelTextField.setText(s);
    }


    /*+++++++++++++++++++++++++++++ SCRIPT AREA +++++++++++++++++++++++++++++*/
    private Node scriptArea() {

        //vb.setPrefHeight(Main.HEIGHT / 2);
        lowerScrollPane = new ScrollPane();
        //vBoxLowerHolder = new VBox();

        lowerText = new TextArea();
        //vBoxLowerHolder.getChildren().add(lowerText);
        //list in middle
        //lowerScrollPane.setFitToHeight(true);
        lowerScrollPane.setFitToWidth(true);
        //lowerScrollPane.setPrefHeight(Main.HEIGHT / 2);
        lowerScrollPane.setContent(lowerText);
        //pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        lowerScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return lowerScrollPane;

    }

    /*+++++++++++++++++++++++++++++ BUTTONS +++++++++++++++++++++++++++++*/

    private Node buttons() {
        VBox buttonsVB = new VBox();
        buttonsVB.setSpacing(5);
        buttonsVB.getChildren().add(commandsMenuButton());

        HBox hb = new HBox(
                runBt(),
                saveBt(),
                deleteBt()
        );
        hb.setSpacing(5);

        buttonsVB.getChildren().add(hb);
        return buttonsVB;
    }

    /* BUTTONS +++++++++++++++++++++++++++++ COMMANDS SELECTOR +++++++++++++++++++++++++++++*/
    private Node commandsMenuButton() {
        HBox hb = new HBox();

        commandMenuButton = new MenuButton("Commands");
        commandMenuButton.getItems().addAll(commandMenuItems());

        Button clearBt = new Button("Clear");
        clearBt.setOnAction(event -> updateLowerPane(""));

        hb.getChildren().add(commandMenuButton);
        hb.getChildren().add(clearBt);

        return hb;
    }

    private MenuItem[] commandMenuItems() {
        return new MenuItem[]{makeMenuItem("mouseMove"), makeMenuItem("mouseClick"), makeMenuItem("mouseHold"),
                makeMenuItem("mouseRelease"), makeMenuItem("keyClick"), makeMenuItem("keyHold"),
                makeMenuItem("keyRelease"), makeMenuItem("wait"),
                makeMenuItem("repeat")
        };
    }

    private MenuItem makeMenuItem(String desc) {
        MenuItem m = new MenuItem(desc);
        m.setOnAction(event -> {
            lowerText.setText(lowerText.getText() + desc + "/\n");
        });
        return m;
    }

    /* BUTTONS +++++++++++++++++++++++++++++ ACTIONS +++++++++++++++++++++++++++++*/

    private Node runBt() {
        Button btt = new Button("Run");
        btt.setOnAction(event -> {
            if (!lowerLabelTextField.getText().equals("") ) {
                Main.scriptWrapper.runScript(MainWindow.BASE_DIR + lowerLabelTextField.getText());
            }
        });
        return btt;
    }

    private Node saveBt() {
        Button save_button = new Button("Save");
        save_button.setOnAction(event -> {
            if(lowerLabelTextField.getText() != null ) {

                Main.scriptWrapper.addScript(lowerLabelTextField.getText(), lowerText.getText());
                displaySection.updateScriptNames();
            }
        });
        return save_button;
    }

    private Node deleteBt() {
        Button bt = new Button("Delete");
        bt.setOnAction(event -> {
            if(lowerLabelTextField.getText() != null ) {
                Main.scriptWrapper.deleteScript(lowerLabelTextField.getText());
                displaySection.updateScriptNames();
            }
        });
        return bt;
    }


    /*+++++++++++++++++++++++++++++ AUX +++++++++++++++++++++++++++++*/
    public static void updateLowerPane(String list) {
        lowerText.setText(list);
    }

    public static void updateLowerPanelLabel(String append) {
        append = append.replaceAll(".txt", "");
        lowerLabelTextField.setText(append);
    }

    private DisplaySection displaySection;
    public void setConnection(DisplaySection d) {
        displaySection = d;
    }

}
