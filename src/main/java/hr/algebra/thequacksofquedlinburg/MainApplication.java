package hr.algebra.thequacksofquedlinburg;

import hr.algebra.thequacksofquedlinburg.Controllers.BoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("PlayerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 650);
        ((BoardController) fxmlLoader.getController()).setStage(stage);
        stage.setTitle("The Quacks of Quedlinburg");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }

}