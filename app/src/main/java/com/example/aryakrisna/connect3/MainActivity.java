package com.example.aryakrisna.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 1;

    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    int[][] winPos = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};

    boolean gameOver = false;

    public void click(View view){

        ImageView slot = (ImageView) view;

        int tappedSlot = Integer.parseInt(slot.getTag().toString());

        if(gameState[tappedSlot - 1] == 0 && !gameOver) {

            gameState[tappedSlot - 1] = player;

            slot.setTranslationY(-1000);

            if (player == 1) {
                slot.setImageResource(R.drawable.yellow);
                player++;
            } else {
                slot.setImageResource(R.drawable.red);
                player--;
            }


            slot.animate().translationYBy(1000).setDuration(300);

            for (int[] s : winPos) {

                if (gameState[s[0] - 1] == gameState[s[1] - 1] && gameState[s[1] - 1] == gameState[s[2] - 1] && gameState[s[0] - 1] != 0) {
                    String winner = "";

                    gameOver = true;
                    if (player == 2) {
                        winner = "yellow";
                    } else {
                        winner = "red";
                    }

                    Button playAgain = (Button) findViewById(R.id.playAgain);

                    TextView message = (TextView) findViewById(R.id.message);

                    message.setText(winner + " has won! Press button to play again!");
                    message.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){

        Button playAgain = (Button) findViewById(R.id.playAgain);

        TextView message = (TextView) findViewById(R.id.message);

        message.setVisibility(View.INVISIBLE);

        playAgain.setVisibility(View.INVISIBLE);

        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 0;
        }

        GridLayout g = (GridLayout) findViewById(R.id.grid);

        for (int i = 0; i < g.getChildCount(); i++){
            ImageView child = (ImageView) g.getChildAt(i);
            child.setImageDrawable(null);
        }

        player = 1;

        gameOver = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
