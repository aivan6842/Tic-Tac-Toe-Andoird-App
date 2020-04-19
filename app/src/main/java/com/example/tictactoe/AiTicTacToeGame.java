package com.example.tictactoe;

public class AiTicTacToeGame {

    private CellValue[][] board;
    private int level;

    public AiTicTacToeGame(){
        this.board = new CellValue[3][3];
        level =0;
        fillEmpty();
    }

     private void fillEmpty(){
        for (int i=0;i<3;i++){
            for (int j =0;j<3;j++){
                board[i][j] = CellValue.EMPTY;
            }
        }
    }

    protected void play(int row, int col){
        if (row >= 3 || row < 0 || col >= 3 || col < 0){
            throw new IllegalArgumentException("Outside the board");
        }
        if (board[row][col] != CellValue.EMPTY){
           throw new IllegalArgumentException();
        }
        board[row][col] = nextCellValue();
        level++;

    }

    protected int checkWin(){
        for (int i=0;i<3;i++){
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                if (board[i][0] == CellValue.X){
                    return 10;
                }
                else if (board[i][0] == CellValue.O){
                    return -10;
                }
            }
        }

        for (int j=0;j<3;j++){
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j]){
                if (board[0][j] == CellValue.X){
                    return 10;
                }
                else if (board[0][j] == CellValue.O){
                    return -10;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]){
            if (board[0][0] == CellValue.X){
                return 10;
            }
            else if (board[0][0] == CellValue.O){
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]){
            if (board[0][2] == CellValue.X){
                return 10;
            }
            else if (board[0][2] == CellValue.O){
                return -10;
            }
        }

        return 0;
    }


    private boolean movesLeft(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (board[i][j] == CellValue.EMPTY){
                    return true;
                }
            }
        }
        return false;
    }

    private int minimax(int depth, Boolean isMax){
        int score = checkWin();


        if (score == 10){
            return score;
        }

        if (score == -10){
            return score;
        }

        if (!movesLeft()){
            return 0;
        }

        if (isMax){
            int best = -1000;
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (board[i][j] == CellValue.EMPTY){
                        board[i][j] = CellValue.X;
                        best = Math.max(best, minimax(depth +1, !isMax));
                        board[i][j] = CellValue.EMPTY;
                    }
                }
            }
            return best - depth;
        }

        else{
            int best = 1000;
            for (int i=0;i<3;i++){
                for (int j=0;j<3;j++){
                    if (board[i][j] == CellValue.EMPTY){
                        board[i][j] = CellValue.O;
                        best = Math.min(best, minimax(depth + 1, !isMax));
                        board[i][j] = CellValue.EMPTY;
                    }
                }
            }
            return best + depth;
        }
    }

    protected int[] findBestMove(){
        int bestVal = -1000;
        int[] move = new int[] {-1, -1};

        for (int i=0;i<3;i++){
            for (int j =0;j<3;j++){
                if (board[i][j] == CellValue.EMPTY){
                    board[i][j] = CellValue.X;
                    int moveVal = minimax(0, false);
                    board[i][j] = CellValue.EMPTY;
                    if (moveVal > bestVal){
                        move[0] = i;
                        move[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return move;
    }

    public CellValue valueAt(int row, int col){
        return board[row][col];
    }

    protected CellValue nextCellValue(){
        return (level%2 == 0) ? CellValue.X : CellValue.O;
    }


}

