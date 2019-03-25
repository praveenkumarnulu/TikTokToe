package com.example.tiktaktoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    // 0: Yellow, 1: Red, 2: Empty
    int activePlayer = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    Boolean gameActive = true;
    Boolean failed = true;
    int timer = 0;

    public void playAgain(View view) {
        Log.i("inside playagian", "first");
        Button playAgainButton = findViewById(R.id.playAgainButton);
        TextView winnerTextView = findViewById(R.id.winnerTextView);

        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        Log.i("inside playagian", "second");
        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout1);
        Log.i("inside playagian", "third");
        failed = true;
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            // do stuff with child view
            counter.setImageDrawable(null);
        }
        for (int j=0; j< gameState.length; j++){
            gameState[j] = 2;
        }

        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {


        ImageView counter = (ImageView) view;
        //Log.i("Tag", "counter.getTag().toString()");

        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if (gameState[tappedCounter] == 2 && gameActive) {
            timer = timer + 1;
            Log.i("timer", Integer.toString(timer));
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-2000);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(2000).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    //someone has won
                    gameActive = false;
                    failed = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    TextView winnerTextView = findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won");

                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    timer = 0;

                }
            }
            if (failed && timer == 9){
                Button playAgainButton = findViewById(R.id.playAgainButton);
                TextView winnerTextView = findViewById(R.id.winnerTextView);
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setText("No player has won!");
                winnerTextView.setVisibility(View.VISIBLE);
                timer = 0;
            }
        }
    }


}

