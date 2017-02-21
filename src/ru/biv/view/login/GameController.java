package ru.biv.view.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.biv.MainApp;
import ru.biv.msgSystem.UserSession;

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

    @FXML
    private Button logOutButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

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

    public void initSessionID(LogInManager logInManager, UserSession userSession) {
        nameLabel.setText(nameLabel.getText()+userSession.getUserName());
        idLabel.setText(idLabel.getText()+userSession.getUserId(userSession.getUserName()).toString());
        logOutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logInManager.logout();
            }
        });
    }
    public void setNameLabelText(String userName) {
        nameLabel.setText("Имя пользователя: "+userName);
    }
    public void setIdLabelText(Integer userId) {
        idLabel.setText("Id пользователя:"+userId.toString());
    }
}
