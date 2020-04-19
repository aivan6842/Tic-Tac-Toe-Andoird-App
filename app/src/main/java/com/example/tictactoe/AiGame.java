package com.example.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AiGame extends AppCompatActivity {

    private Integer[][] buttons;
    private AiTicTacToeGame game;
    protected static Activity AiGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_game);

        startGame();
    }

    private void startGame(){
        AiGame = this;
        game = new AiTicTacToeGame();
        buttons = new Integer[3][3];
        List<Integer> allButtons = getAllButtons();
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                Button currButton = (Button) findViewById(allButtons.get(i));
                buttons[i][j] = allButtons.get(i);
                currButton.setText("");
            }
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

    private List<Integer> getIndexOfButton(View v){
        List<Integer> buttonIndex = new ArrayList<>();
        int buttonId = v.getId();
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (buttons[i][j] == buttonId){
                    buttonIndex.add(i);
                    buttonIndex.add(j);
                    return buttonIndex;
                }
            }
        }
        throw new IllegalArgumentException("Shouldnt be happening");
    }

    protected void playGame(View v){
        if (game.checkWin() == 10 || game.checkWin() == -10){
            return;
        }

        List<Integer> buttonIndex = getIndexOfButton(v);
        int row = buttonIndex.get(0);
        int col = buttonIndex.get(1);

        if (game.valueAt(row, col) != CellValue.EMPTY){
            return;
        }

        game.play(row, col);
        Button playedButton = (Button) findViewById(v.getId());
        playedButton.setText(game.valueAt(row, col).toString());

        if (game.checkWin() == 10 || game.checkWin() == -10){
            System.out.println("Won");
        }
    }
}

