package ru.biv.view.login;

import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import ru.biv.MainApp;
import ru.biv.msgSystem.UserSession;
import ru.biv.network.ObjectRequestSender;
import ru.biv.network.UserAuth;
import ru.biv.view.game.GameController;

public class LogInController {

    @FXML
    private Label responseResult;

    @FXML
    private TextField inputNick;

    @FXML
    private ProgressIndicator progressIndicator;

    // Ссылка на главное приложение.
    //private MainApp mainApp;

    private static String userName;
    private UserAuth userAuth;
    private double indicatorOpacity = 0;

    private Thread th;


    //private Thread threadAuth;

    /*public Task task = new Task<String>() {
        @Override public String call() {
            while (true) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
                    break;
                }
                //response = RequestSender.sendGetRequest(LOG_URL, userName);
                updateMessage(RequestSender.sendGetRequest(LOG_URL, userName));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException interrupted) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                        break;
                    }
                }
            }
            return userName;
        }
    };*/

    public Task taskObject = new Task<String>() {
        @Override public String call() throws NullPointerException, InterruptedException {
            while (th.isAlive()) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
                    break;
                }
                progressIndicator.setOpacity(indicatorOpacity);
                userAuth.setUserName(userName);
                System.out.println("Имя пользователя: "+userAuth.getUserAuth().getUserName()+"\tId пользователя: "+userAuth.getUserAuth().getUserId(userAuth.getUserAuth().getUserName()));
                updateMessage("Имя пользователя: "+userAuth.getUserAuth().getUserName()+"\tId пользователя: "+userAuth.getUserAuth().getUserId(userAuth.getUserAuth().getUserName()));
                if (userAuth.getUserAuth().getAuth() == "AUTH") {
                    showGameForm();
                    taskObject.cancel(true);
                    th.wait();
                }
                try {
                    Thread.sleep(250);
                } catch (NullPointerException|InterruptedException ex) {
                    taskObject.cancel(true);
                    break;
                }
            }
            return userName;
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
        userAuth = new UserAuth();
        responseResult.textProperty().bind(taskObject.messageProperty());
        th = new Thread(taskObject);
        th.setDaemon(true);
        th.start();
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    //public void setMainApp(MainApp mainApp) {
    //    this.mainApp = mainApp;
    //}

    public void handleLogInButton() {
        if (inputNick.getText().equals(null) || inputNick.getText().equals("")) {
            responseResult.setText("The input field can't be empty. Input your nick, please.");
        } else {
            userName = inputNick.getText();//"userName=" + inputNick.getText();
            indicatorOpacity = 1;
        }
    }

    @NotNull
    public void showGameForm() {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                MainApp.showGameForm();
            }
        });
    }
}
