package ru.biv;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import ru.biv.model.Config;
import ru.biv.model.MainFrame;
import ru.biv.msgSystem.UserSession;
import ru.biv.network.ObjectRequestSender;
import ru.biv.network.UserAuth;
import ru.biv.view.login.AuthController;
import ru.biv.view.login.LogInController;
import ru.biv.view.login.LogInManager;
import sun.rmi.runtime.Log;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MainApp extends Application {

    private static Stage primaryStage;

    private MainFrame mainFrame;

    //private static AuthController controller;

    //public String userName;

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*MainApp.primaryStage = primaryStage;
        MainApp.primaryStage.setTitle("HumanGo");
        MainApp.primaryStage.setResizable(false);

        initRootLayout();

        showLogInForm();*/

        //AuthService authService = new AuthService();
        //authService.setMainApp(this);
        //authService.start();
        Scene scene = new Scene(new StackPane());

        LogInManager logInManager = new LogInManager(scene, primaryStage);
        logInManager.showLoginView();

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /*public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/login/LogInForm.fxml"));
            GridPane rootLayout = (GridPane)loader.load();

            Scene scene = new Scene(rootLayout);
            MainApp.primaryStage.setScene(scene);
            MainApp.primaryStage.setTitle("HumanGo");
            MainApp.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    /*public void showLogInForm() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/login/LogInForm.fxml"));
            GridPane logInForm = (GridPane) loader.load();

            // Даём контроллеру доступ к главному приложению.
            //LogInController controller = loader.getController();
            //System.out.println("From " + this.getClass() + " controller hashcode " + hashCode());
            //controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showGameForm() {
        try {
            FXMLLoader gameLoader = new FXMLLoader();//(getClass().getResource("view/game/GameForm.fxml"));
            gameLoader.setLocation(MainApp.class.getResource("view/game/GameForm.fxml"));
            //primaryStage.hide();

            SplitPane gameForm = (SplitPane) gameLoader.load();
            Config.initialize();
            Group root = new Group();
            root.getChildren().add(gameForm);
            mainFrame = new MainFrame(root);

            // Создаём диалоговое окно Stage.
            //primaryStage = new Stage();
            primaryStage.setTitle("HumanGo");
            primaryStage.setWidth(1200);
            primaryStage.setHeight(925);
            primaryStage.setResizable(false);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            //stage.setWidth(Config.SCREEN_WIDTH + 2*Config.WINDOW_BORDER);
            //stage.setHeight(Config.SCREEN_HEIGHT + 2.5*Config.WINDOW_BORDER);
            //Scene scene = new Scene(root);

            //stage.setScene(scene);
            mainFrame.startGame();

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
