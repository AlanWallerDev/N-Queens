package com.example.t00053669.n_queensproblem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Arrays;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    public static int BOARD_SIZE = 5;
    public static final String TAG = "Main Activity";
    public static BoardState currentState = new BoardState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentState.setBoard(BoardUtils.boardInit(BOARD_SIZE));
        boolean isValid = BoardUtils.isValid(1, 2, currentState.getBoard());
        Log.d(TAG, "Position validity: " + isValid);
        currentState.placeQueen(1, 2);
        Log.d(TAG, "Current Board: \n" + twoDimArrayToString(currentState.getBoard()));
        isValid = BoardUtils.isValid(2, 4, currentState.getBoard());
        Log.d(TAG, "Position validity of [2][4]: " + isValid);
        currentState.placeQueen(2, 4);
        Log.d(TAG, "Current Board: \n" + twoDimArrayToString(currentState.getBoard()));
        isValid = BoardUtils.isValid(3, 3, currentState.getBoard());
        Log.d(TAG, "Position validity of [3][3]: " + isValid);
        currentState.placeQueen(3, 3);
        Log.d(TAG, "Current Board: \n" + twoDimArrayToString(currentState.getBoard()));

    }

    public static String twoDimArrayToString(int[][] array){
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int[] row : array) {
            sj.add(Arrays.toString(row));
        }
        String result = sj.toString();
        return result;
    }
}
