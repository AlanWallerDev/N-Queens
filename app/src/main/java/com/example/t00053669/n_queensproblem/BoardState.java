package com.example.t00053669.n_queensproblem;

/**
 * Created by t00053669 on 3/28/2018.
 */

public class BoardState {

    public static int[][] board;
    public static boolean isVisited;

    public BoardState() {
        isVisited = false;
        board = new int[0][0];
    }

    public static int[][] getBoard() {
        return board;
    }

    public static void setBoard(int[][] board) {
        BoardState.board = board;
    }

    public static boolean isIsVisited() {
        return isVisited;
    }

    public static void setIsVisited(boolean isVisited) {
        BoardState.isVisited = isVisited;
    }

    public static void placeQueen(int column, int row){
        board[row][column] = 1;
    }
}
