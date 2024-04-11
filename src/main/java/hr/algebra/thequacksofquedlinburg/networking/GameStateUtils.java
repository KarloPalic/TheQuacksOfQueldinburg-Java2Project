package hr.algebra.thequacksofquedlinburg.networking;

import hr.algebra.thequacksofquedlinburg.controllers.BoardController;
import hr.algebra.thequacksofquedlinburg.controllers.PlayerPotController;
import hr.algebra.thequacksofquedlinburg.gameBoard.GameBoardUtils;
import hr.algebra.thequacksofquedlinburg.gameBoard.GameState;
import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameStateUtils {
    public static GameState createGameState(int player1Points, int player2Points, int turnCount, Team currentTurn, PlayerPotController playerPotController1, PlayerPotController playerPotController2, Circle player1Circle, Circle player2Circle, int player1CircleBoardPosX, int player1CircleBoardPosY, int player2CircleBoardPosX, int player2CircleBoardPosY, Stage stage){
        GameState gameState = new GameState(player1Points, player2Points, turnCount, currentTurn, playerPotController1, playerPotController2);

        gameState.setPlayer1CircleBoardPosX(player1CircleBoardPosX);
        gameState.setPlayer1CircleBoardPosY(player1CircleBoardPosY);
        gameState.setPlayer2CircleBoardPosX(player2CircleBoardPosX);
        gameState.setPlayer2CircleBoardPosY(player2CircleBoardPosY);

        if (player1Circle != null) {
            gameState.setPlayer1Circle(player1Circle);
        }
        if (player2Circle != null) {
            gameState.setPlayer2Circle(player2Circle);
        }

        gameState.setClickable(stage);

        String winner = gameState.getWinner(player1Points, player2Points, turnCount);
        if (!winner.isEmpty()) {
            GameBoardUtils.showMessage(winner);
            stage.close();
        }
        gameState.clearCircles();

        return gameState;
    }

    public static void retoreGameState(GridPane mainGridPane, GameState restoredGameState, int player1Points, int player2Points, int turnCount, Team currentTurn, PlayerPotController playerPotController1, PlayerPotController playerPotController2, Circle player1Circle, Circle player2Circle, int player1CircleBoardPosX, int player1CircleBoardPosY, int player2CircleBoardPosX, int player2CircleBoardPosY){

        player1Points = restoredGameState.getPlayer1Points();
        player2Points = restoredGameState.getPlayer2Points();
        turnCount = restoredGameState.getTurnCount();
        currentTurn = restoredGameState.getCurrentTurn();
        playerPotController1 = restoredGameState.getPlayerPotController1();
        playerPotController2 = restoredGameState.getPlayerPotController2();

        player1CircleBoardPosX = restoredGameState.getPlayer1CircleBoardPosX();
        player1CircleBoardPosY = restoredGameState.getPlayer1CircleBoardPosY();

        player2CircleBoardPosX = restoredGameState.getPlayer2CircleBoardPosX();
        player2CircleBoardPosY = restoredGameState.getPlayer2CircleBoardPosY();

        player1Circle = restoredGameState.getPlayer1Circle();
        player2Circle = restoredGameState.getPlayer2Circle();
    }
}
