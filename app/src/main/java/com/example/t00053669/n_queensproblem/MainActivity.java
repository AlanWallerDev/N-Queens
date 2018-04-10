package com.example.t00053669.n_queensproblem;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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
    public boolean solvable = false;
    public static TextView solutionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        solutionView = (TextView) findViewById(R.id.solutionView);
        final EditText bsView = (EditText) findViewById(R.id.boardSizeView);
        Button solutionButton = (Button) findViewById(R.id.bsButton);
        solutionView.setHorizontallyScrolling(true);

        solutionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final WorkerThread thread = new WorkerThread(MainActivity.this);
                thread.execute(Integer.parseInt(bsView.getText().toString()));

            }
        });


    }

    public static String twoDimArrayToString(int[][] array) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (int[] row : array) {
            sj.add(Arrays.toString(row));
        }
        String result = sj.toString();
        return result;
    }

    public boolean solve() {
        int col = 0;
        if (col >= BOARD_SIZE) {
            //this means it has been solved
            return true;
        }
        for (int i = 0; i < BOARD_SIZE; i++) {

            if (BoardUtils.isValid(col, i, currentState.getBoard())) {
                currentState.placeQueen(col, i);
                if (solve(col + 1) == true) {
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

    private class WorkerThread extends AsyncTask<Integer, Void, Boolean> {
        private ProgressDialog dialog;
        public WorkerThread(MainActivity activity){
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Processing, this could take a while...");
            dialog.show();
        }

        @Override
        protected void onPostExecute(Boolean b) {
            super.onPostExecute(b);
            dialog.dismiss();
            Log.d(TAG, "Done!");
            if(b){
                solutionView.setText(BOARD_SIZE + "\n" + twoDimArrayToString(currentState.getBoard()));
            }else{
                Toast.makeText(MainActivity.this, "Minimum Board Size is 4", Toast.LENGTH_SHORT).show();
            }
            currentState.setBoard(BoardUtils.boardInit(BOARD_SIZE));
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            if(integers[0] < 4)
                return false;
            BOARD_SIZE = integers[0];
            currentState.setBoard(BoardUtils.boardInit(BOARD_SIZE));
            boolean result = solve();

            return result;
        }
    }
}
