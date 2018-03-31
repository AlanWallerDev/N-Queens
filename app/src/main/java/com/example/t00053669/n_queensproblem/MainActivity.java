package com.example.t00053669.n_queensproblem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.StringJoiner;

public class MainActivity extends AppCompatActivity {

    public static int BOARD_SIZE = 16;
    public static final String TAG = "Main Activity";
    public static BoardState currentState = new BoardState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView solutionView = (TextView) findViewById(R.id.solutionView);
        final EditText bsView = (EditText) findViewById(R.id.boardSizeView);
        Button solutionButton = (Button) findViewById(R.id.bsButton);
        solutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int input = Integer.parseInt(bsView.getText().toString());
                if(input < 4) {
                    Toast.makeText(MainActivity.this, "Minimum Board Size is 4", Toast.LENGTH_SHORT).show();
                }
                else if(input > 24) {
                    Toast.makeText(MainActivity.this, "Maximum Board Size is 24", Toast.LENGTH_SHORT).show();
                }else {
                    BOARD_SIZE = Integer.parseInt(bsView.getText().toString());

                    currentState.setBoard(BoardUtils.boardInit(BOARD_SIZE));
                    if (solve(0) == false) {
                        solutionView.setText("No Solution Found");
                    } else {
                        solutionView.setText("Solution Found! \n" + twoDimArrayToString(currentState.getBoard()));
                    }
                    currentState.setBoard(BoardUtils.boardInit(BOARD_SIZE));
                }
            }
        });



    }

    public static String twoDimArrayToString(int[][] array){
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int[] row : array) {
            sj.add(Arrays.toString(row));
        }
        String result = sj.toString();
        return result;
    }

    public boolean solve( int col){
        if(col >= BOARD_SIZE){
            //this means it has been solved
            return true;
        }
        for(int i = 0; i < BOARD_SIZE;i++){

            if(BoardUtils.isValid(col, i, currentState.getBoard())){
                currentState.placeQueen(col, i);
                if(solve( col + 1) == true){
                    return true;
                }
                //if that didnt work backtrack
                int tempBoard[][] = currentState.getBoard();
                tempBoard[i][col] = 0;
                currentState.setBoard(tempBoard);
            }

        }
        return false;
    }

}
