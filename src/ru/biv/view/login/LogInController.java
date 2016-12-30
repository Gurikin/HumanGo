package ru.biv.view.login;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import ru.biv.MainApp;
import ru.biv.msgSystem.UserSession;
import ru.biv.network.ObjectRequestSender;
import ru.biv.network.RequestSender;

public class LogInController {

    @FXML
    private Label responseResult;

    @FXML
    private TextField inputNick;

    @FXML
    private ProgressIndicator progressIndicator;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    private static final String LOG_URL = "http://localhost:8080/hello";

    private static String parameters;
    private String response;
    private UserSession userSession;

    public Task task = new Task<String>() {
        @Override public String call() {
            while (true) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
                    break;
                }
                //response = RequestSender.sendGetRequest(LOG_URL, parameters);
                updateMessage(RequestSender.sendGetRequest(LOG_URL, parameters));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                        break;
                    }
                }
            }
            return parameters;
        }
    };

    public Task taskObject = new Task<String>() {
        @Override public String call() {
            while (true) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
                    break;
                }
                userSession = ObjectRequestSender.sendGetRequest(LOG_URL, parameters);
                updateMessage("Имя пользователя: "+userSession.getUserName()+"\tId пользователя: "+userSession.getUserId(userSession.getUserName()));
                if (userSession.getUserId(userSession.getUserName()) != null) {
                    progressIndicator.setOpacity(0);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                        break;
                    }
                }
            }
            return parameters;
        }
    };

    public LogInController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        responseResult.textProperty().bind(taskObject.messageProperty());
        Thread th = new Thread(taskObject);
        th.setDaemon(true);
        th.start();
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void handleLogInButton() {
        if (inputNick.getText().equals(null) || inputNick.getText().equals("")) {
            responseResult.setText("The input field can't be empty. Input your nick, please.");
        } else {
            parameters = "userName=" + inputNick.getText();
            progressIndicator.setOpacity(1);
        }
    }
}
