package com.example.t00053669.n_queensproblem;

import android.util.Log;

/**
 * Created by t00053669 on 3/28/2018.
 */

public class BoardUtils {

    public static final String TAG = "BoardUtils";

    public BoardUtils() {
    }
    //creates a 2d array populated with 0's
    public static int[][] boardInit(int n){
        int[][] board = new int[n][n];
        for(int i = 0; i < board.length; i++)
            for(int j = 0; i < board.length; i++)
                board[i][j] = 0; //0 means there are no queens in that position

        return board;
    }
    //checks if the given column and row is a valid location to place a queen
    public static boolean isValid(int column, int row, int[][] board){
        //checks for straight line collisions in column and row
        for (int i=0; i < board.length; i++){
            if(board[row][i] == 1){
                return false;
            }
            if(board[i][column] == 1)
                return false;
        }
        int rowCheck = row+1;
        int colCheck = column+1;
        //check up and to the right diagonal

            while (rowCheck < board.length && colCheck < board.length) {
                if (board[rowCheck][colCheck] == 1) {
                    return false;
                }
                rowCheck++;
                colCheck++;
            }

        rowCheck = row+1;
        colCheck = column - 1;
        //check up and to the left diagonal

            while (rowCheck < board.length && colCheck >= 0) {
                if (board[rowCheck][colCheck] == 1)
                    return false;
                rowCheck++;
                colCheck--;
            }
        rowCheck = row - 1;
        colCheck = column - 1;
        //check down and to the left diagonal

            while (rowCheck >= 0 && colCheck >= 0) {
                if (board[rowCheck][colCheck] == 1)
                    return false;
                rowCheck--;
                colCheck--;
            }


        rowCheck = row-1;
        colCheck = column+1;
        //check down and to the right diagonal

            while (rowCheck >= 0 && colCheck < board.length) {
                if (board[rowCheck][colCheck] == 1)
                    return false;
                rowCheck--;
                colCheck++;
            }

        return true;
    }

}
