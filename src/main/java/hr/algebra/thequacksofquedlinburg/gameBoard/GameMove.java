package hr.algebra.thequacksofquedlinburg.gameBoard;

import hr.algebra.thequacksofquedlinburg.gameBoard.enums.Team;
import javafx.util.Pair;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GameMove implements Serializable {

    private static final long serialVersionUID = 1L;
    private Team playerColor;
    private String playerPosition;
    private LocalDateTime dateTime;

    public GameMove(Team playerColor, String playerPosition, LocalDateTime dateTime) {
        this.playerColor = playerColor;
        this.playerPosition = playerPosition;
        this.dateTime = dateTime;
    }

    public Team getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Team playerColor) {
        this.playerColor = playerColor;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
