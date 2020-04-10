package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ComputerGame extends AppCompatActivity {

    private CellValue[] board;
    private Integer[] buttons;
    private int level;
    private GameState gameState;
    private TicTacToeGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_game);
        startGame();
    }

    private void startGame(){
        game = new TicTacToeGame();
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
        game.play(buttonIndex);
        Button playedButton = (Button) findViewById(v.getId());
        playedButton.setText(game.valueAt(buttonIndex).toString());

        if (game.getGameState() != GameState.PLAYING){
            //startActivity(new Intent(this, EndPopUp.class));
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



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_computer_game);
//        startGame();
//    }
//
//    private void startGame(){
//        level =0;
//        gameState = GameState.PLAYING;
//        board = new CellValue[9];
//        buttons = new Integer[9];
//        fillEmptyBoard();
//    }
//
//    public GameState getGameState(){
//        return gameState;
//    }
//
//    private CellValue getNextTurn(){
//        if (level % 2 == 0){
//            return CellValue.X;
//        }
//        else{
//            return CellValue.O;
//        }
//    }
//
//    private boolean validButton(int index){
//        return board[index] == CellValue.EMPTY;
//    }
//
//    private void makeMove(int index){
//        board[index] = getNextTurn();
//        Button currButton = (Button)findViewById(buttons[index]);
//        currButton.setText(board[index].toString());
//
//    }
//
//    public void turn(int index){
//        if (!validButton(index)){
//            return;
//        }
//        makeMove(index);
//        level++;
//    }
//
//    private int getIndexOfButton(View v){
//        Integer buttonid = v.getId();
//        for (int i=0;i<9;i++){
//            if (buttons[i].equals(buttonid)){
//                return i;
//            }
//        }
//        throw new IllegalArgumentException("Shouldn't be happening");
//    }
//
//    public void play(View v){
//        if (gameState != GameState.PLAYING){
//            return;
//        }
//        int moveIndex = getIndexOfButton(v);
//        turn(moveIndex);
//        setGameState(moveIndex);
//        System.out.println(Arrays.toString(board));
//        System.out.println(gameState);
//        System.out.println(isWon());
//        if (gameState != GameState.PLAYING){
//            //startActivity(new Intent(this, EndPopUp.class));
//        }
//    }
//
//    private void fillEmptyBoard(){
//        List<Integer> allButtons = getAllButtons();
//        for (int i=0;i<allButtons.size();i++){
//            Integer currId = allButtons.get(i);
//            Button currButton = (Button) findViewById(currId);
//            buttons[i] = currId;
//            currButton.setText("");
//            board[i] = CellValue.EMPTY;
//        }
//    }
//
//    private List<Integer> getAllButtons(){
//        List<Integer> ret = new ArrayList<>();
//        for (int i =0; i < 9;i++){
//            int id = getResources().getIdentifier("button_" + i, "id", getPackageName());
//            ret.add(id);
//        }
//        return ret;
//    }
//
//
//    private void setGameState(int index){
//        CellValue curr = getNextTurn();
//        if (gameState == GameState.XWON || gameState == GameState.OWON){
//            return;
//        }
//        else if (isWon()){
//            switch (curr){
//                case X:
//                    gameState = GameState.OWON;
//                    break;
//                case O:
//                    gameState = GameState.XWON;
//                    break;
//            }
//        }
//        else if (level == 8){
//            gameState = GameState.DRAW;
//        }
//        else{
//            gameState = GameState.PLAYING;
//        }
//    }
//
//    private boolean isWon(){
//        boolean horz1 = board[0] == board[1] && board[1] == board[2] && board[0] != CellValue.EMPTY;
//        boolean horz2 = board[3] == board[4] && board[4] == board[5] && board[3] != CellValue.EMPTY;
//        boolean horz3 = board[6] == board[7] && board[7] == board[8] && board[6] != CellValue.EMPTY;
//
//        boolean vert1 = board[0] == board[3] && board[3] == board[6] && board[0] != CellValue.EMPTY;
//        boolean vert2 = board[1] == board[4] && board[4] == board[7] && board[1] != CellValue.EMPTY;
//        boolean vert3 = board[2] == board[5] && board[5] == board[8] && board[2] != CellValue.EMPTY;
//
//        boolean rDiag = board[0] == board[4] && board[4] == board[8] && board[0] != CellValue.EMPTY;
//        boolean lDiag = board[2] == board[4] && board[4] == board[6] && board[2] != CellValue.EMPTY;
//
//        return horz1 || horz2 || horz3 || vert1 || vert2 || vert3 || rDiag || lDiag;
//    }
}
