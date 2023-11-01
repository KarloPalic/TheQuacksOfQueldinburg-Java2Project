package hr.algebra.thequacksofquedlinburg.Conrollers;

import hr.algebra.thequacksofquedlinburg.MainApplication;
import hr.algebra.thequacksofquedlinburg.gameBoard.MainBoard;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    private ImageView player1Icon;
    @FXML
    private ImageView player2Icon;
    @FXML
    private AnchorPane playerPointsPane;
    private PlayerPotController playerPotController1;
    private PlayerPotController playerPotController2;

    private int turnCount = 0;

    private Team currentTurn = Team.Blue;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainBoard mainBoard = new MainBoard();
        mainBoard.createGridPane(mainGridPane);
        mainBoard.layoutGridPane(mainGridPane);

        setDraggable(player1Icon);
        player1Icon.setTranslateX(-627);
        player1Icon.setTranslateY(170);

        setDraggable(player2Icon);
        player2Icon.setTranslateX(-620);
        player2Icon.setTranslateY(136);




    }
    @FXML
    public void openPot1(ActionEvent event){
        if (currentTurn == Team.Blue){
            openPot(Team.Blue, "PotView1.fxml");
        }else {
            showMessage("It is not your turn");
        }

    }
    @FXML
    public void openPot2(ActionEvent event){
        if (currentTurn == Team.Red){
            openPot(Team.Red, "PotView2.fxml");
        }else {
            showMessage("It is not your turn");
        }
    }


    private void openPot(Team team, String fxmlFileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFileName));
            Parent root = fxmlLoader.load();

            PlayerPotController controller = fxmlLoader.getController();
            controller.setTeam(team);
            controller.setStage(new Stage());

            controller.setBoardController(this);

            Stage stage1 = new Stage();
            stage1.setTitle("Player " + team.name() + " Pot");
            stage1.setScene(new Scene(root));
            stage1.show();

            controller.setStage(stage1);

            if (team == Team.Blue) {
                controller.onbtnEndTurnPlayer1Clicked(null);
                playerPotController1 = controller;
            } else {
                controller.onbtnEndTurnPlayer2Clicked(null);
                playerPotController2 = controller;
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        turnCount++;
        if (turnCount >= 19) {
            determineWinner();
        }
        currentTurn = (currentTurn == Team.Blue) ? Team.Red : Team.Blue;
    }
    private void setDraggable (ImageView image){
        final PlayerPotController.Delta dragDelta = new PlayerPotController.Delta();

        image.setOnMousePressed(event -> {
            dragDelta.x = event.getSceneX() - image.getLayoutX();
            dragDelta.y = event.getSceneY() - image.getLayoutY();
            image.setCursor(Cursor.MOVE);
        });

        image.setOnMouseDragged(event -> {
            image.setLayoutX(event.getSceneX() - dragDelta.x);
            image.setLayoutY(event.getSceneY() - dragDelta.y);
        });

        image.setOnMouseReleased(event -> {
            image.setCursor(Cursor.HAND);
        });
    }
    private void determineWinner() {
        if (playerPotController1 == null) {
            return;
        }
        if (playerPotController2 == null) {
            return;
        }

        int pointsPlayer1 = playerPotController1.getPlayer1Points();
        int pointsPlayer2 = playerPotController2.getPlayer2Points();

        String winner;
        if (pointsPlayer1 > pointsPlayer2) {
            winner = "Player 1 wins";
        } else if (pointsPlayer2 > pointsPlayer1) {
            winner = "Player 2 wins";
        } else {
            winner = "It's a tie!";
        }

        showMessage(winner);
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addLabelToPlayerPointsPane(Label label) {
        playerPointsPane.getChildren().add(label);
    }

    public void removePlayer1Label() {
        playerPointsPane.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().startsWith("Player 1 "));
    }

    public void removePlayer2Label() {
        playerPointsPane.getChildren().removeIf(node -> node instanceof Label && ((Label) node).getText().startsWith("Player 2 "));
    }
}
