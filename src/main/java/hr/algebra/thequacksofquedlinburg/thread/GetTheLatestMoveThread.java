package hr.algebra.thequacksofquedlinburg.thread;

import hr.algebra.thequacksofquedlinburg.gameBoard.GameMove;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class GetTheLatestMoveThread extends GameMoveThread implements Runnable{
    private Label theLastMoveLabel;
    public GetTheLatestMoveThread(Label theLastMoveLabel){
        this.theLastMoveLabel = theLastMoveLabel;
    }
    @Override
    public void run() {

        while (true) {
            Platform.runLater(() -> {
                GameMove theLastMove = getLastMove();

                theLastMoveLabel.setText("The Last Move:- " +
                        "Player color: " + theLastMove.getPlayerColor() + ", " +
                        "Player position: " + theLastMove.getPlayerPosition() + ", " +
                        "Time of move: " + theLastMove.getDateTime());

            });

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
