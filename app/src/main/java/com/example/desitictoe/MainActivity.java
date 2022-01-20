package com.example.desitictoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 9 empty states

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0; // 0 for ravan

    boolean gameActive = true;


    public void dropin(View view) {

        ImageView counter = (ImageView) view; // counter is the tapped image (view)
        Log.i("Tag", counter.getTag().toString());

        int TappedCounter = Integer.parseInt(counter.getTag().toString()); // takes the counter tag

        if (gameState[TappedCounter] == 2 && gameActive) { // to avoid tapping  on tapped item already

            gameState[TappedCounter] = activePlayer;  // takes the active player into the array


            counter.setTranslationY(-1500);   // animation part

            if (activePlayer == 0) {


                counter.setImageResource(R.drawable.ravan);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.chota);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(400);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    // someonee has won

                    gameActive = false;

                    String Winner = "";
                    if (activePlayer == 1) {

                        Winner = " RAAVAN  ";
                    } else {
                        Winner = "CHOTA BHEEM";
                    }


                    Button playAgain = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(Winner + " has won !!");
                    playAgain.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        Button playAgain = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) { // loops through grid layout to remove all tags

            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null); // set all images to null

        }

            for(int i  =0; i < gameState.length; i++){

                gameState[i] = 2;
            }


             activePlayer = 0; // 0 for ravan

                  gameActive = true;


            playAgain.setVisibility(View.INVISIBLE);
            winnerTextView.setVisibility(View.INVISIBLE);
        }

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}