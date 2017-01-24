package ru.biv.view.game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.biv.MainApp;

import java.io.IOException;

/**
 * Created by Игорь on 24.12.2016.
 */
public class GameController {
    @FXML
    private AnchorPane gameField;

    @FXML
    private AnchorPane gameStatictic;

    @FXML
    private AnchorPane userStatistic;

    private Stage gameStage;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        //mainApp = new MainApp();
        //responseResult.textProperty().bind(taskObject.messageProperty());
        //Thread th = new Thread(taskObject);
        //th.setDaemon(true);
        //th.start();
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage gameStage) {
        this.gameStage = gameStage;
    }

    public void showGameForm() {
        try {
            System.out.println("1");
                FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("GameForm.fxml"));
            System.out.println("2");
            //gameLoader.setLocation(MainApp.class.getResource("GameForm.fxml"));
                //SplitPane gameForm = (SplitPane) loader.load();
            System.out.println("3");
                Parent gameForm = (Parent) gameLoader.load();

                // Создаём диалоговое окно Stage.
            System.out.println("4");
                Stage gameStage = new Stage();
            System.out.println("5");
                gameStage.setTitle("HumanGo");
                //gameStage.initModality(Modality.WINDOW_MODAL);
            System.out.println("6");
                gameStage.initModality(Modality.APPLICATION_MODAL);
                //gameStage.initOwner(primaryStage);
            System.out.println("7");
                Scene scene = new Scene(gameForm);
            System.out.println("8");
                gameStage.setScene(scene);

                // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
                //gameStage.showAndWait();
            System.out.println("84");
                gameStage.show();
                System.out.println("gameStage:" + gameStage.toString());
                System.out.println("loader:" + gameLoader.toString());
                System.out.println("scene:" + scene.toString());
                //primaryStage.close();
        } catch (IOException e) {
                e.printStackTrace();
          }
    }
}
