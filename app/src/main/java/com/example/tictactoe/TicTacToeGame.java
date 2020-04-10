package com.example.tictactoe;

public class TicTacToeGame {
    private CellValue[] board;
    private int level;
    private GameState gameState;

    public TicTacToeGame(){
        board = new CellValue[9];
        for (int i=0;i<9;i++){
            board[i] = CellValue.EMPTY;
        }
        level=0;
        gameState = GameState.PLAYING;
    }

    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(getClass() != o.getClass()){
            return false;
        }

        TicTacToeGame other = (TicTacToeGame)o;

        if(level != other.level){
            return false;
        }
        for(int i = 0; i < board.length ; i++ ) {
            if(board[i]!= other.board[i]) {
                return false;
            }
        }
        return true;
    }

    public int getLevel(){
        return level;
    }

    public GameState getGameState(){
        return gameState;
    }

    public CellValue nextCellValue(){
        return (level%2 == 0) ? CellValue.X : CellValue.O;
    }

    public CellValue valueAt(int i) {

        if(i < 0 || i >= 9){
            throw new IllegalArgumentException("Illegal position: " + i);
        }

        return board[i];
    }

    public void play(int i) {
        if(i < 0 || i >= 9){
            throw new IllegalArgumentException("Illegal position: " + i);
        }
        if(board[i] != CellValue.EMPTY) {
            throw new IllegalArgumentException("CellValue not empty: " + i + " in game " + toString());
        }

        board[i] = nextCellValue();
        level++;
        if(gameState != GameState.PLAYING) {
            System.out.println("hum, extending a finished game... keeping original winner");
        } else {
            setGameState(i);
        }
    }

    private void setGameState(int index){
        CellValue curr = nextCellValue();
        if (gameState == GameState.XWON || gameState == GameState.OWON){
            return;
        }
        else if (isWon()){
            switch (curr){
                case X:
                    gameState = GameState.OWON;
                    break;
                case O:
                    gameState = GameState.XWON;
                    break;
            }
        }
        else if (level == 8){
            gameState = GameState.DRAW;
        }
        else{
            gameState = GameState.PLAYING;
        }
    }

    private boolean isWon(){
        boolean horz1 = board[0] == board[1] && board[1] == board[2] && board[0] != CellValue.EMPTY;
        boolean horz2 = board[3] == board[4] && board[4] == board[5] && board[3] != CellValue.EMPTY;
        boolean horz3 = board[6] == board[7] && board[7] == board[8] && board[6] != CellValue.EMPTY;

        boolean vert1 = board[0] == board[3] && board[3] == board[6] && board[0] != CellValue.EMPTY;
        boolean vert2 = board[1] == board[4] && board[4] == board[7] && board[1] != CellValue.EMPTY;
        boolean vert3 = board[2] == board[5] && board[5] == board[8] && board[2] != CellValue.EMPTY;

        boolean rDiag = board[0] == board[4] && board[4] == board[8] && board[0] != CellValue.EMPTY;
        boolean lDiag = board[2] == board[4] && board[4] == board[6] && board[2] != CellValue.EMPTY;

        return horz1 || horz2 || horz3 || vert1 || vert2 || vert3 || rDiag || lDiag;
    }

}
