package com.example.tictactoe;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndGamePopUp extends AppCompatActivity {

    protected static boolean active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8), (int)(height*0.5));

        addWinnerText();
    }

    private void addWinnerText(){
        TicTacToeGame game = (TicTacToeGame)getIntent().getSerializableExtra("TicTacToe");
        int  textViewId = getResources().getIdentifier("textViewWinner" , "id", getPackageName());
        TextView curr = (TextView) findViewById(textViewId);
        GameState endGameState = game.getGameState();
        if (endGameState == GameState.XWON){
            curr.setText("X Wins!");
        }
        else if (endGameState == GameState.OWON){
            curr.setText("O Wins!");
        }
        else {
            curr.setText("Draw!");
        }
    }

    public void goBackHome(View view){
        finish();
        ComputerGame.compGame.finish();
    }
}
