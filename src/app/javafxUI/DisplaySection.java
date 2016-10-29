package app.javafxUI;

import app.Main;
import app.script.ScriptReader;
import app.utils.MyFiles;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

import static app.javafxUI.MainWindow.RightSide.RIGHT_TEXT_FONT;

/**
 * Created on 18/10/2016.
 */

public class DisplaySection extends VBox {

    public static final String upperPanelLabel = "Scripts";


    private ScrollPane upperPane;
    private VBox vBoxScriptNames;

    public DisplaySection() {
        this.getChildren().addAll(header(), ScriptNamesPane());
    }

    /*+++++++++++++++++++++++++++++ HEADER +++++++++++++++++++++++++++++*/
    private Node header() {
        HBox hb = new HBox(
                title(upperPanelLabel),
                refreshFilesButton()
        );
        hb.setSpacing(5);

        return hb;
    }

    private Text title(String s) {
        Text txt = new Text(s);
        txt.setFont(new Font(RIGHT_TEXT_FONT, 18));
        return txt;
    }

    private Node refreshFilesButton() {
        Button b = new Button("F5");
        b.setOnAction(event -> {
            updateScriptNames();

        });

        return b;
    }

    /*+++++++++++++++++++++++++++++ SCRIPT NAMES +++++++++++++++++++++++++++++*/
    private Node ScriptNamesPane() {
        upperPane = new ScrollPane();

        updateScriptNames();

        upperPane.setFitToHeight(true);
        upperPane.setFitToWidth(true);
        upperPane.setPrefHeight(Main.HEIGHT / 2);
        //upperPane.setContent(vBoxScriptNames);
        //pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        upperPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        return upperPane;

    }

    public void updateScriptNames() {
        vBoxScriptNames = new VBox();

        List<String> names = Main.scriptWrapper.loadScripts("/scripts/");
        for (String s : names) {
            Text txt = new Text(s);
            handleNameClicked(txt);
            txt.setFont(new Font(RIGHT_TEXT_FONT, 16));
            vBoxScriptNames.getChildren().add(txt);
        }
        upperPane.setContent(vBoxScriptNames);
    }


    private void handleNameClicked(Text txt) {
        txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String scriptName = txt.getText();
                if (event.getClickCount() == 1) {
                    handleOneClick(scriptName);
                } else if (event.getClickCount() == 2) {
                    handleTwoClick(scriptName);
                }

            }
        });

    }

    private void handleTwoClick(String scriptName) {
        try {
            ProcessBuilder pb = new ProcessBuilder("Notepad.exe", MyFiles.getCurrentDir() + ScriptReader.baseDir + scriptName);
            pb.start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleOneClick(String scriptName) {
        if (isFolder(scriptName)) { //TODO
            System.out.println("folder!");
        } else if (isScript(scriptName)) {
            String list = Main.scriptWrapper.loadScript(MainWindow.BASE_DIR + scriptName);
            InputSection.updateLowerPane(list);
            InputSection.updateLowerPanelLabel(scriptName);
        }
    }


    private boolean isFolder(String text) { //TODO
        return text.contains(".cs");
    }

    private boolean isScript(String text) {
        return text.contains(".txt");
    }

     /*+++++++++++++++++++++++++++++ AUX - PUBLIC +++++++++++++++++++++++++++++*/



    InputSection inputSection;
    public void setConnection(InputSection c) {
       inputSection = c;
    }
}
