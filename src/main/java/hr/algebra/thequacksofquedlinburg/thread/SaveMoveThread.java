package hr.algebra.thequacksofquedlinburg.thread;

import hr.algebra.thequacksofquedlinburg.gameBoard.GameMove;

public class SaveMoveThread extends GameMoveThread implements Runnable{

    private GameMove gameMove;
    public SaveMoveThread(GameMove gameMove){
        this.gameMove = gameMove;
    }
    @Override
    public void run() {
        saveMove(gameMove);
    }
}
