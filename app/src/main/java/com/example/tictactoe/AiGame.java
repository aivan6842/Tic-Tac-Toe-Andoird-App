package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
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
        int z=0;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                Button currButton = (Button) findViewById(allButtons.get(z));
                buttons[i][j] = allButtons.get(z);
                currButton.setText("");
                z++;
            }
        }

        int[] bestMove = game.findBestMove();
        int row = bestMove[0];
        int col = bestMove[1];
        game.play(row, col);
        Button playedButton = (Button) findViewById(buttons[row][col]);
        playedButton.setText(game.valueAt(row, col).toString());

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

    public void playGame(View v){
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
        Button playedButton = (Button) findViewById(buttons[row][col]);
        CellValue cellValue = game.valueAt(row, col);
        playedButton.setText(cellValue.toString());

//        System.out.println(game.getGameState());
//        System.out.println(Arrays.toString(game.board[0]) + "\n" + Arrays.toString(game.board[1]) + "\n" + Arrays.toString(game.board[2]));

        if (game.getGameState() != GameState.PLAYING){
            throw new IllegalStateException("Should not be winning on O move");
        }
        else{
            int[] bestMove = game.findBestMove();
            int bestrow = bestMove[0];
            int bestcol = bestMove[1];
            game.play(bestrow, bestcol);
            Button playedAiButton = (Button) findViewById(buttons[bestrow][bestcol]);
            CellValue cellValuebest = game.valueAt(bestrow, bestcol);
            playedAiButton.setText(cellValuebest.toString());

            if (game.getGameState() != GameState.PLAYING){
                Intent intent = new Intent(this, EndGamePopUp.class);
                intent.putExtra("TicTacToe", game);
                startActivity(intent);
            }
        }


    }
}

