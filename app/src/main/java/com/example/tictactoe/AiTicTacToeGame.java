package com.example.tictactoe;

import java.util.Arrays;

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

//    private int[] getMoveLocation(){
//        boolean validnum = false;
//        boolean validpos = false;
//        int row=-1;
//        int col=-1;
//        while(!validpos){
//            while(!validnum){
//                try{
//                    System.out.println("Enter a row number: ");
//                    if (sc.hasNextInt()){
//                        row = sc.nextInt()-1;
//                    }
//                    else{
//                        sc.next();
//                        continue;
//                    }
//                    if (row >= 0 && row <= 2){
//                        validnum = true;
//                    }
//                }
//                catch(Exception e){
//                    System.out.println("Please enter a valid integer between 1 and 3.");
//                }
//            }
//            validnum = false;
//            while(!validnum){
//                try{
//                    System.out.println("Enter a column number: ");
//                    if (sc.hasNextInt()){
//                        col = sc.nextInt()-1;
//                    }
//                    else{
//                        sc.next();
//                        continue;
//                    }
//                    if (col >= 0 && col <= 2){
//                        System.out.println("yes");
//                        validnum = true;
//                    }
//                }
//                catch(Exception e){
//                    System.out.println("Please enter a valid integer between 1 and 3.");
//                }
//            }
//            if (board[row][col] == CellValue.EMPTY){
//                validpos = true;
//            }
//            else{
//                System.out.println("This position has already been played.");
//                validnum=false;
//            }
//        }
//        return new int[] {row, col};
//    }

    protected void play(int row, int col){
        if (row >= 3 || row < 0 || col >= 3 || col < 0){
            throw new IllegalArgumentException("Outside the board");
        }
        if (board[row][col] != CellValue.EMPTY){
           throw new IllegalArgumentException();
        }

        System.out.println(nextCellValue());
        board[row][col] = nextCellValue();
        level++;
        System.out.println(valueAt(row, col));
        System.out.println(Arrays.toString(board[0]) + "\n" + Arrays.toString(board[1]) + "\n" + Arrays.toString(board[2]));
    }





//    private void play(){
//        do {
//            int[] aiMove = findBestMove();
//            board[aiMove[0]][aiMove[1]] = CellValue.X;
//            System.out.println(this);
//            if (movesLeft() && checkWin() != 10 && checkWin() != -10){
//                int[] personMove = getMoveLocation();
//                board[personMove[0]][personMove[1]] = CellValue.O;
//            }
//        }while(movesLeft() && checkWin() != 10 && checkWin() != -10);
//        if (!movesLeft()){
//            System.out.println("Draw!");
//        }
//        else if (checkWin() == 10){
//            System.out.println("X Won!");
//        }
//        else{
//            System.out.println("O Won!");
//        }
//        sc.close();
//    }

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

