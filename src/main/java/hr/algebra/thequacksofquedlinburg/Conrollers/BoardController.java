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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;

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

    private int player1Points;
    private int player2Points;

    private Circle player1Circle;
    private Circle player2Circle;

    private Stage stage;
    private int turnCount = 0;

    private Team currentTurn = Team.Blue;

    public void setStage(Stage stage){
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        MainBoard mainBoard = new MainBoard();
        mainBoard.createGridPane(mainGridPane);
        mainBoard.layoutGridPane(mainGridPane);


        player1Icon.setTranslateX(-627);
        player1Icon.setTranslateY(170);


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

    public void addPlayer1Points(int points){
        player1Points += points;
    }

    public void addPlayer2Points(int points){
        player2Points += points;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
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
        this.stage.close();
        playerPotController1.getStage().close();
        playerPotController2.getStage().close();
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

    public void movePlayer1(int movePlayer){
        player1Circle = new Circle(10);
        player1Circle.setFill(Color.BLUE);
        Pair player1BoardPosition = getBoardPosition(movePlayer);
        mainGridPane.add(player1Circle, (int) player1BoardPosition.getKey(),(int) player1BoardPosition.getValue());
    };

    public void erasePlayer1(int erasePlayer){
        mainGridPane.getChildren().remove(player1Circle);
    };

    public void movePlayer2(int movePlayer){
        player2Circle = new Circle(10);
        player2Circle.setFill(Color.RED);
        Pair player2BoardPosition = getBoardPosition(movePlayer);
        mainGridPane.add(player2Circle, (int) player2BoardPosition.getKey(),(int) player2BoardPosition.getValue());
    };

    public void erasePlayer2(int erasePlayer){
        mainGridPane.getChildren().remove(player2Circle);
    };

    public Pair<Integer,Integer> getBoardPosition(int playerPos)
    {
        switch(playerPos)
        {
            case 1:
                return new Pair(0,11);
            case 2:
                return new Pair(0,10);
            case 3:
                return new Pair(0,9);
            case 4:
                return new Pair(0,8);
            case 5:
                return new Pair(0,7);
            case 6:
                return new Pair(0,6);
            case 7:
                return new Pair(0,5);
            case 8:
                return new Pair(0,4);
            case 9:
                return new Pair(0,3);
            case 10:
                return new Pair(0,2);
            case 11:
                return new Pair(0,1);
            case 12:
                return new Pair(0,0);
            case 13:
                return new Pair(1,0);
            case 14:
                return new Pair(2,0);
            case 15:
                return new Pair(3,0);
            case 16:
                return new Pair(4,0);
            case 17:
                return new Pair(5,0);
            case 18:
                return new Pair(6,0);
            case 19:
                return new Pair(7,0);
            case 20:
                return new Pair(8,0);
            case 21:
                return new Pair(9,0);
            case 22:
                return new Pair(10,0);
            case 23:
                return new Pair(11,0);
            case 24:
                return new Pair(12,0);
            case 25:
                return new Pair(13,0);
            case 26:
                return new Pair(14,0);
            case 27:
                return new Pair(14,1);
            case 28:
                return new Pair(14,2);
            case 29:
                return new Pair(14,3);
            case 30:
                return new Pair(14,4);
            case 31:
                return new Pair(14,5);
            case 32:
                return new Pair(14,6);
            case 33:
                return new Pair(14,7);
            case 34:
                return new Pair(14,8);
            case 35:
                return new Pair(14,9);
            case 36:
                return new Pair(14,10);
            case 37:
                return new Pair(14,11);
            case 38:
                return new Pair(13,11);
            case 39:
                return new Pair(12,11);
            case 40:
                return new Pair(11,11);
            case 41:
                return new Pair(10,11);
            case 42:
                return new Pair(9,11);
            case 43:
                return new Pair(8,11);
            case 44:
                return new Pair(7,11);
            case 45:
                return new Pair(6,11);
            case 46:
                return new Pair(5,11);
            case 47:
                return new Pair(4,11);
            case 48:
                return new Pair(3,11);
            case 49:
                return new Pair(2,11);
            case 50:
                return new Pair(1,11);
            default:
                return new Pair<>(0,0);

        }


    };
}
