package hr.algebra.thequacksofquedlinburg.gameBoard;

import javafx.scene.control.Alert;
public class GameBoardUtils {
    private static String winner = "";
    public static void determineWinner(int pointsPlayer1, int pointsPlayer2) {
            if (pointsPlayer1 > pointsPlayer2) {
                winner = "Player 1 wins";
            } else if (pointsPlayer2 > pointsPlayer1) {
                winner = "Player 2 wins";
            } else {
                winner = "It's a tie!";
            }
            showMessage(winner);
    }

    public static void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quack Alert");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
