package hr.algebra.thequacksofquedlinburg.Conrollers;

import hr.algebra.thequacksofquedlinburg.MainApplication;
import hr.algebra.thequacksofquedlinburg.gameBoard.MainBoard;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    @FXML
    private GridPane mainGridPane;
    @FXML
    private ImageView player1icon;
    @FXML
    private ImageView player2icon;

    private Team currentTurn = Team.Blue;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainBoard mainBoard = new MainBoard();
        mainBoard.createGridPane(mainGridPane);
        mainBoard.layoutGridPane(mainGridPane);

        player1icon.setTranslateY(-53);
        player1icon.setTranslateX(-623);

        player2icon.setTranslateY(-66);
        player2icon.setTranslateX(-620);

    }
    @FXML
    public void openPot1(ActionEvent event){
        openPot(Team.Blue);

        /*if (isPlayer1Turn){
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("PotView1.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage1 = new Stage();
                stage1.setTitle("Player 1 Pot");
                stage1.setScene(new Scene(root));
                stage1.show();

                isPlayer1Turn = false;

            } catch (IOException e) {

                e.printStackTrace();
            }
        }*/

    }
    @FXML
    public void openPot2(ActionEvent event){
        openPot(Team.Red);

       /* if (!isPlayer1Turn){
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("PotView2.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage2 = new Stage();
                stage2.setTitle("Player 2 Pot");
                stage2.setScene(new Scene(root));
                stage2.show();

                isPlayer1Turn = true;

            } catch (IOException e) {

                e.printStackTrace();
            }
        }*/

    }


    private void openPot(Team team) {
        try {

            if (currentTurn != team) return;

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("PotView1.fxml"));
            Parent root = fxmlLoader.load();

            PlayerPotController controller = fxmlLoader.getController();
            controller.setTeam(team);

            Stage stage1 = new Stage();
            stage1.setTitle("Player " + team.name() + " Pot");
            stage1.setScene(new Scene(root));
            stage1.show();

            if (currentTurn == Team.Blue){
                currentTurn = Team.Red;
            }
            else {
                currentTurn = Team.Blue;
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
