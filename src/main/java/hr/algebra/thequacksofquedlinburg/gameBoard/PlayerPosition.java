package hr.algebra.thequacksofquedlinburg.gameBoard;

import javafx.util.Pair;

import java.io.Serializable;

public class PlayerPosition implements Serializable {
    public static Pair<Integer,Integer> getBoardPosition(int playerPos)
    {
        return switch (playerPos) {
            case 1 -> new Pair(0, 11);
            case 2 -> new Pair(0, 10);
            case 3 -> new Pair(0, 9);
            case 4 -> new Pair(0, 8);
            case 5 -> new Pair(0, 7);
            case 6 -> new Pair(0, 6);
            case 7 -> new Pair(0, 5);
            case 8 -> new Pair(0, 4);
            case 9 -> new Pair(0, 3);
            case 10 -> new Pair(0, 2);
            case 11 -> new Pair(0, 1);
            case 12 -> new Pair(0, 0);
            case 13 -> new Pair(1, 0);
            case 14 -> new Pair(2, 0);
            case 15 -> new Pair(3, 0);
            case 16 -> new Pair(4, 0);
            case 17 -> new Pair(5, 0);
            case 18 -> new Pair(6, 0);
            case 19 -> new Pair(7, 0);
            case 20 -> new Pair(8, 0);
            case 21 -> new Pair(9, 0);
            case 22 -> new Pair(10, 0);
            case 23 -> new Pair(11, 0);
            case 24 -> new Pair(12, 0);
            case 25 -> new Pair(13, 0);
            case 26 -> new Pair(14, 0);
            case 27 -> new Pair(14, 1);
            case 28 -> new Pair(14, 2);
            case 29 -> new Pair(14, 3);
            case 30 -> new Pair(14, 4);
            case 31 -> new Pair(14, 5);
            case 32 -> new Pair(14, 6);
            case 33 -> new Pair(14, 7);
            case 34 -> new Pair(14, 8);
            case 35 -> new Pair(14, 9);
            case 36 -> new Pair(14, 10);
            case 37 -> new Pair(14, 11);
            case 38 -> new Pair(13, 11);
            case 39 -> new Pair(12, 11);
            case 40 -> new Pair(11, 11);
            case 41 -> new Pair(10, 11);
            case 42 -> new Pair(9, 11);
            case 43 -> new Pair(8, 11);
            case 44 -> new Pair(7, 11);
            case 45 -> new Pair(6, 11);
            case 46 -> new Pair(5, 11);
            case 47 -> new Pair(4, 11);
            case 48 -> new Pair(3, 11);
            case 49 -> new Pair(2, 11);
            case 50 -> new Pair(1, 11);
            default -> new Pair<>(2, 8);
        };


    };
    public static String formatPairAsString(Pair<Integer, Integer> pair) {
        return pair.getKey() + "," + pair.getValue();
    }
}
