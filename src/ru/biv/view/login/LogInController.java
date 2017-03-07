package ru.biv.view.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;


public class LogInController {

    @FXML
    private Label responseResult;

    @FXML
    private TextField inputNick;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Button loginButton;

    private String userName;

    public LogInController() {
    }

    public void setProgressIndicatorOpacity(double opacity) {
        progressIndicator.setOpacity(opacity);
    }

    public void setResponseResultText(String responseResultText) {
        this.responseResult.setText(responseResultText);
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     * Убрать после создания на стороне сервера GameSession (если не понадобиться)
     */
    @FXML
    private void initialize() {
    }


    @FXML
    public void handleInputNickKeyPressed(javafx.scene.input.KeyEvent e) {
        //System.out.println(e.getCode().ordinal());
        if (e.getCode().ordinal() == 0) {
            handleLogInButton();
        }
    }

    public void handleLogInButton() {
        if (inputNick.getText().equals(null) || inputNick.getText().equals("")) {
            responseResult.setText("The input field can't be empty. Input your nick, please.");
        } else {
            userName = inputNick.getText();//"userName=" + inputNick.getText();
            setProgressIndicatorOpacity(1.0);
        }
    }

    public void initManager(final LogInManager logInManager) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogInButton();
                /*userAuth.setUserName(userName);
                if (userAuth.getUserAuth().getUserId(userAuth.getUserAuth().getUserName()) != null) {
                    logInManager.authenticated(userAuth.getUserAuth());
                }*/
                logInManager.setUserName(userName);
            }
        });
    }
}
