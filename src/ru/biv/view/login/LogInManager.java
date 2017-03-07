package ru.biv.view.login;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import ru.biv.model.Config;
import ru.biv.model.MainFrame;
import ru.biv.msgSystem.UserSession;
import ru.biv.network.UserAuth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Игорь on 16.02.2017.
 */
public class LogInManager {
    private Scene scene;
    private Stage primaryStage;
    private MainFrame mainFrame;
    private UserAuth userAuth;
    private Thread th;
    private GameController gameController;
    private LogInController logInController;

    public void setResponseResultText(String responseResult) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                logInController.setResponseResultText(responseResult);
            }
        });
    }

    public LogInManager(Scene scene, Stage primaryStage) {
        this.scene = scene;
        this.primaryStage = primaryStage;
        userAuth = new UserAuth();
        th = new Thread(taskObject);
        th.setDaemon(true);
        th.start();
    }

    public Task taskObject = new Task<Void>() {
        @Override public Void call() throws NullPointerException, InterruptedException {
            while (true) {
                if (isCancelled()) {
                    updateMessage("Cancelled");
                    break;
                }
                //System.out.println("FROM contr:\t\tИмя пользователя: "+userAuth.getUserAuth().getUserName()+"\tId пользователя: "+userAuth.getUserAuth().getUserId(userAuth.getUserAuth().getUserName()));
                if (userAuth.getUserAuth().getAuth() == "AUTHORIZATION") {
                    setResponseResultText("Авторизация на подходе. Подождите, пожалуйста.");
                }
                if (userAuth.getUserAuth().getAuth() == "AUTH") {
                    setResponseResultText("Авторизация прошла успешно. Игра загружается.");
                    if (gameController != null) {
                        setUserData(userAuth.getUserAuth());
                    } else {
                        showGameForm(userAuth.getUserAuth());
                    }
                }
                if (userAuth.getUserAuth().getAuth() == "DONT_AUTH") {
                    setResponseResultText("Не удалось найти пользователя с таким именем. Попробуйте ещё раз.");
                    logInController.setProgressIndicatorOpacity(0.0);
                }
                try {
                    Thread.sleep(1000);
                } catch (NullPointerException|InterruptedException ex) {
                    taskObject.cancel(true);
                    break;
                }
            }
            return null;
        }
    };
    
    public void logout() {
        gameController = null;
        setUserName(null);
        showLoginView();
    }

    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("LogInForm.fxml")
            );
            primaryStage.setTitle("HumanGo");
            primaryStage.setWidth(590);
            primaryStage.setHeight(440);
            primaryStage.setResizable(false);
            scene.setRoot((Parent)loader.load());
            logInController = loader.<LogInController>getController();
            logInController.initManager(this);
        } catch (IOException ioEx) {
            Logger.getLogger(LogInManager.class.getName()).log(Level.SEVERE, null, ioEx);
        }
    }

    private void showGameView(UserSession userSession) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("GameForm.fxml")
            );

            SplitPane gameForm = (SplitPane) loader.load();
            Config.initialize();
            Group root = new Group();
            root.getChildren().add(gameForm);
            mainFrame = new MainFrame(root);

            primaryStage.setTitle("HumanGo");
            primaryStage.setWidth(1200);
            primaryStage.setHeight(925);
            primaryStage.setResizable(false);

            scene.setRoot((Parent) root);

            gameController = loader.<GameController>getController();
            gameController.initSessionID(this, userSession);

            mainFrame.startGame();
            primaryStage.setScene(scene);
        } catch(IOException ioEx) {
            Logger.getLogger(LogInManager.class.getName()).log(Level.SEVERE, null, ioEx);
        }
    }

    public void setUserName(String userName) {
        this.userAuth.setUserName(userName);
    }

    public void showGameForm(UserSession userSession) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                showGameView(userSession);
            }
        });
    }
    private void setUserData(UserSession userSession) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
                gameController.setLabelsText(userSession);//userSession.getUserName();
                mainFrame.getGameParty().setMousePress(userSession);
                System.out.println(userSession.getLastStep()[0] + "\t" + userSession.getLastStep()[1]);
                //userSession.getUserId(userAuth.getUserAuth().getUserName());
            }
        });
    }

    private Integer checkGameSessionStatus(){
        switch (userAuth.getUserAuth().getPartyStatus()) {
            case -1:
                break;
            case 0:
                break;
            case 1:
                break;
        }
        //TODO necessary to complete this method with the event on every gameSessionStatus
        return null;
    }
}
