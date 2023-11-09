package hr.algebra.thequacksofquedlinburg.GameBoard;

import hr.algebra.thequacksofquedlinburg.Controllers.PlayerPotController;
import hr.algebra.thequacksofquedlinburg.GameBoard.enums.Team;
import javafx.scene.shape.Circle;

import java.io.Serializable;

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
    public GameState(int player1Points, int player2Points, int turnCount, Team currentTurn, PlayerPotController playerPotController1, PlayerPotController playerPotController2, Circle player1Circle, Circle player2Circle, int player1CircleBoardPosX, int player1CircleBoardPosY, int player2CircleBoardPosX, int player2CircleBoardPosY) {
        this.player1Points = player1Points;
        this.player2Points = player2Points;
        this.turnCount = turnCount;
        this.currentTurn = currentTurn;
        this.playerPotController1 = playerPotController1;
        this.playerPotController2 = playerPotController2;
        this.player1Circle = player1Circle;
        this.player2Circle = player2Circle;
        this.player1CircleBoardPosX = player1CircleBoardPosX;
        this.player1CircleBoardPosY = player1CircleBoardPosY;
        this.player2CircleBoardPosX = player2CircleBoardPosX;
        this.player2CircleBoardPosY = player2CircleBoardPosY;
    }
}
