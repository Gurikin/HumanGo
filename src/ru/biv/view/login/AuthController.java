package ru.biv.view.login;

import com.sun.istack.internal.NotNull;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import ru.biv.MainApp;
import ru.biv.network.UserAuth;

import java.util.Optional;


public class AuthController {

    @FXML
    private Label responseResult;

    @FXML
    private TextField inputNick;

    @FXML
    private ProgressIndicator progressIndicator;

    // Ссылка на главное приложение.
    public MainApp mainApp;

    private String userName;
    private UserAuth userAuth;
    private double indicatorOpacity = 0;

    public AuthController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        responseResult.setText("Введите, пожалуйста имя пользователя.");
    }

    @FXML
    public void handleInputNickKeyPressed(javafx.scene.input.KeyEvent e) {
        if (e.getCode().ordinal() == 0) {
            handleLogInButton();
        }
    }

    public void handleLogInButton() {
        if (inputNick.getText().equals(null) || inputNick.getText().equals("")) {
            responseResult.setText("The input field can't be empty. Input your nick, please.");
        } else {
            userName = inputNick.getText();//"userName=" + inputNick.getText();
            TextInputDialog dialog = new TextInputDialog("Enter name, please.");
            dialog.setTitle("Name, please");
            dialog.setHeaderText("Name, please, please");
            dialog.setContentText("Enter your name, please");
            Optional<String> result = dialog.showAndWait();
            //indicatorOpacity = 1;
        }
    }

    public void setResponseResult(String responseResult) {
        this.responseResult.setText(responseResult);
    }

    public String getUserName() {
        return userName;
    }
}
