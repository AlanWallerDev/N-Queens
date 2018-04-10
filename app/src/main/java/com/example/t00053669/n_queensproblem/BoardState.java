package com.example.t00053669.n_queensproblem;

/**
 * Created by t00053669 on 3/28/2018.
 */

public class BoardState {

    public static int[][] board;

    public BoardState() {
        board = new int[0][0];
    }

    public static int[][] getBoard() {
        return board;
    }

    public static void setBoard(int[][] board) {
        BoardState.board = board;
    }


    public static void placeQueen(int column, int row){
        board[row][column] = 1;
    }
}
