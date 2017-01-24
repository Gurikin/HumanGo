package ru.biv.model;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by Игорь on 23.12.2016.
 */
public class TestMain extends Application{
    private static MainFrame mainFrame;

    public static MainFrame getMainFrame() {
        return mainFrame;
    }
    @Override
    public void start(Stage stage) throws Exception {
        Config.initialize();
        Group root = new Group();
        mainFrame = new MainFrame(root);
        stage.setTitle("HumanGo");
        stage.setResizable(false);
        stage.setWidth(Config.SCREEN_WIDTH + 2*Config.WINDOW_BORDER);
        stage.setHeight(Config.SCREEN_HEIGHT + 2.5*Config.WINDOW_BORDER);
        Scene scene = new Scene(root);
        scene.setFill(Color.ANTIQUEWHITE);
        stage.setScene(scene);
        mainFrame.startGame();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
