package hr.algebra.thequacksofquedlinburg.thread;

import hr.algebra.thequacksofquedlinburg.gameBoard.GameMove;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class GameMoveThread {

    private static boolean fileAccessInProgress = false;
    private static final String MOVES_FILE = "files/moves.dat";

    public synchronized void saveMove(GameMove gameMove){
        while (fileAccessInProgress){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        fileAccessInProgress = true;

        List<GameMove> gameMoves = getAllLastMoves();
        gameMoves.add(gameMove);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MOVES_FILE))){
            oos.writeObject(gameMoves);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        fileAccessInProgress = false;
        notifyAll();
    }

    public synchronized List<GameMove> getAllLastMoves(){
        List<GameMove> gameMoves = new ArrayList<>();

        if (Files.exists(Path.of(MOVES_FILE))) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MOVES_FILE))) {
                gameMoves.addAll((List<GameMove>) ois.readObject());
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return gameMoves;
    }

    public synchronized GameMove getLastMove(){
        while (fileAccessInProgress){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        fileAccessInProgress = true;

        List<GameMove> gameMoves = getAllLastMoves();

        fileAccessInProgress = false;
        notifyAll();
        return gameMoves.get(gameMoves.size() - 1);
    }
}
