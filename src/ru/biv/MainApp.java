package ru.biv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import ru.biv.view.login.LogInController;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private GridPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.setResizable(false);

        initRootLayout();

        showLogInForm();
    }


    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/login/LogInForm.fxml"));
            rootLayout = (GridPane)loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setTitle("HumanGo");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    public void showLogInForm() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/login/LogInForm.fxml"));
            GridPane logInForm = (GridPane) loader.load();

            // Помещаем сведения об адресатах в центр корневого макета.
            //rootLayout.setCenter(personOverview);

            // Даём контроллеру доступ к главному приложению.
            LogInController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void stop() {
        if (!primaryStage.isShowing()) {
            LogInController logInController = new LogInController();
            logInController.task.cancel(true);
        }
    }
}
