package com.example.t00053669.n_queensproblem;

/**
 * Created by t00053669 on 3/28/2018.
 */
//stores the board and has the boards setters and getters, as well as a way to alter the board
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

    //sets the value of given block to 1, to represent a queen being placed there
    public static void placeQueen(int column, int row){
        board[row][column] = 1;
    }
}
