package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ComputerGame extends AppCompatActivity {

    private Integer[] buttons;
    private TwoPlayerTicTacToeGame game;
    protected static Activity compGame;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_game);
        startGame();
    }

    private void startGame(){
        compGame = this;
        game = new TwoPlayerTicTacToeGame();
        buttons = new Integer[9];
        List<Integer> allButtons = getAllButtons();
        for (int i=0;i<allButtons.size();i++){
            Button currButton = (Button) findViewById(allButtons.get(i));
            buttons[i] = allButtons.get(i);
            currButton.setText("");
        }
    }

    private List<Integer> getAllButtons(){
        List<Integer> ret = new ArrayList<>();
        for (int i =0; i < 9;i++){
            int id = getResources().getIdentifier("button_" + i, "id", getPackageName());
            ret.add(id);
        }
        return ret;
    }

    public void playGame(View v){
        if (game.getGameState() != GameState.PLAYING){
            return;
        }

        int buttonIndex = getIndexOfButton(v);

        if (game.valueAt(buttonIndex) != CellValue.EMPTY){
            return;
        }

        game.play(buttonIndex);
        Button playedButton = (Button) findViewById(v.getId());
        playedButton.setText(game.valueAt(buttonIndex).toString());

        if (game.getGameState() != GameState.PLAYING){
            Intent intent = new Intent(this, EndGamePopUp.class);
            intent.putExtra("TicTacToe", game);
            startActivity(intent);
        }
    }


    private int getIndexOfButton(View v){
        Integer buttonid = v.getId();
        for (int i=0;i<9;i++){
            if (buttons[i].equals(buttonid)){
                return i;
            }
        }
        throw new IllegalArgumentException("Shouldn't be happening");
    }

}
