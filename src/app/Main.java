package app;

import app.commands.simulation.mouse.MOUSECODE;
import app.javafxUI.MainWindow;
import app.script.ScriptWrapper;
import app.commands.simulation.mouse.Coord;
import app.script.ScriptWriter;
import app.utils.MyFiles;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class Main extends Application {

    public static Stage activeStage;
    private Scene activeScene;

    private MainWindow mainlayout;

    public static double WIDTH = 1720;
    public static double HEIGHT = 940;

    private String APP_TITLE = "IOROBOT";
    private boolean IS_WINDOW_REZISABLE = false;

    public static Robot robot;

    private static final Coord HermitPos = new Coord(1219, 858);
    public static ScriptWrapper scriptWrapper;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        activeStage = primaryStage;
        activeStage.setTitle(APP_TITLE);


        scriptWrapper = new ScriptWrapper();

        createRobot();
        AddMainWindow();

        //test();

        activeScene = new Scene(mainlayout, WIDTH, HEIGHT);
        activeStage.setScene(activeScene);
        activeStage.setResizable(IS_WINDOW_REZISABLE);
        activeStage.show();
        activeStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.printf("fechar");
            }
        });
    }

    private void test() {



    }

    private void createRobot() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println(e.getMessage());
            activeStage.close();
        }
    }

    private void AddMainWindow() {
        mainlayout = new MainWindow();
    }



}
