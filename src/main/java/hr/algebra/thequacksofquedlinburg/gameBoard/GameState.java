package hr.algebra.thequacksofquedlinburg.gameBoard;

import hr.algebra.thequacksofquedlinburg.MainApplication;
import hr.algebra.thequacksofquedlinburg.controllers.BoardController;
import hr.algebra.thequacksofquedlinburg.controllers.PlayerPotController;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import hr.algebra.thequacksofquedlinburg.networking.PlayerType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;

public class GameState implements Serializable {
    public int player1Points;
    public int player2Points;
    public int turnCount;
    public Team currentTurn;
    public transient PlayerPotController playerPotController1;
    public transient PlayerPotController playerPotController2;

    public transient Circle player1Circle;
    public transient Circle player2Circle;

    public int player1CircleBoardPosX;
    public int player1CircleBoardPosY;
    public int player2CircleBoardPosX;
    public int player2CircleBoardPosY;

    public GameState(int player1Points, int player2Points, int turnCount, Team currentTurn, PlayerPotController playerPotController1, PlayerPotController playerPotController2) {
        this.player1Points = player1Points;
        this.player2Points = player2Points;
        this.turnCount = turnCount;
        this.currentTurn = currentTurn;
        this.playerPotController1 = playerPotController1;
        this.playerPotController2 = playerPotController2;
    }

    public int getPlayer1Points() {
        return player1Points;
    }

    public int getPlayer2Points() {
        return player2Points;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public Team getCurrentTurn() {
        return currentTurn;
    }

    public PlayerPotController getPlayerPotController1() {
        return playerPotController1;
    }

    public PlayerPotController getPlayerPotController2() {
        return playerPotController2;
    }

    public Circle getPlayer1Circle() {
        return player1Circle;
    }

    public Circle getPlayer2Circle() {
        return player2Circle;
    }

    public int getPlayer1CircleBoardPosX() {
        return player1CircleBoardPosX;
    }

    public int getPlayer1CircleBoardPosY() {
        return player1CircleBoardPosY;
    }

    public int getPlayer2CircleBoardPosX() {
        return player2CircleBoardPosX;
    }

    public int getPlayer2CircleBoardPosY() {
        return player2CircleBoardPosY;
    }

    public void clearCircles() {
        this.player1Circle = null;
        this.player2Circle = null;
    }

    public String getWinner(int player1Points, int player2Points, int turnCount) {
        String winner = "";
        if (turnCount == 5) {
            if (player1Points > player2Points) {
                winner = "Player 1 Wins";
            } else if (player2Points > player1Points) {
                winner = "Player 2 Wins";
            } else {
                winner = "It's a Tie";
            }
        }
        return winner;
    }

    public void setClickable(Stage mainStage) {
        if (!MainApplication.playerLoggedIn.name().equals(PlayerType.SINGLE_PLAYER.name())){
            if (MainApplication.playerLoggedIn.name().equals(PlayerType.SERVER.name()) && currentTurn != Team.Red) {
                mainStage.getScene().getRoot().setDisable(true);
            } else if (MainApplication.playerLoggedIn.name().equals(PlayerType.CLIENT.name()) && currentTurn != Team.Blue) {
                mainStage.getScene().getRoot().setDisable(true);
            } else {
                mainStage.getScene().getRoot().setDisable(false);
            }
        }
    }

    public void setPlayer1CircleBoardPosX(int player1CircleBoardPosX) {
        this.player1CircleBoardPosX = player1CircleBoardPosX;
    }

    public void setPlayer1CircleBoardPosY(int player1CircleBoardPosY) {
        this.player1CircleBoardPosY = player1CircleBoardPosY;
    }

    public void setPlayer2CircleBoardPosX(int player2CircleBoardPosX) {
        this.player2CircleBoardPosX = player2CircleBoardPosX;
    }

    public void setPlayer2CircleBoardPosY(int player2CircleBoardPosY) {
        this.player2CircleBoardPosY = player2CircleBoardPosY;
    }

    public void setPlayer1Circle(Circle player1Circle) {
        this.player1Circle = player1Circle;
    }

    public void setPlayer2Circle(Circle player2Circle) {
        this.player2Circle = player2Circle;
    }

}
