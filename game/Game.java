package game;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    char[][] board = {  {'-', '-', '-'},
                        {'-', '-', '-'},
                        {'-', '-', '-'}
                    };

    char currentPlayer = 'x';

    
    Scanner console = new Scanner(System.in);

    public void play(){
        System.out.println("Starting a new game ...");
       
        boolean hasWinner = false;

        printBoard();

        while(!hasWinner)
        {
            // 1. print current board

            


            // 2. get move of the current player

            playerMove();

            printBoard();

            // 3. check if there is a winner
            hasWinner = checkWinner();
        
            System.out.println("Has winner? " + hasWinner);

            if(!hasWinner){
                if(currentPlayer == 'o')
                {
                    currentPlayer = 'x';
                }else{
                    currentPlayer = 'o';
                }
            }
        }

        //   if there is no winner, switch player and go to 1
        //   else announce the winner, and end the game!

        console.close();
    }

    public void printBoard(){
        
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    public boolean checkWinner(){

        // check rows
        for(int i = 0; i < 3; i++){
            if(board[i][0] == currentPlayer 
                && board[i][1] == currentPlayer
                && board[i][2] == currentPlayer
            ){
                return true;
            }
        }

        // check columns
        for(int i = 0; i < 3; i++)
        {
            if(board[0][i] == currentPlayer
                && board[1][i] == currentPlayer
                && board[2][i] == currentPlayer
            ){
                return true;
            }
        }

        // check diagonals
        if(board[0][0] == currentPlayer
            && board[1][1] == currentPlayer
            && board[2][2] == currentPlayer
        ){
            return true;
        }

        if(board[2][0] == currentPlayer
            && board[1][1] == currentPlayer
            && board[0][2] == currentPlayer
        ){
            return true;
        }

        return false;
    }

    public void playerMove()
    {
                    
            int row=0;
            int column=0;
            boolean validMove = false;
            do {
                System.out.println("Please make a valid move. Current player is -" +  currentPlayer + "-");

                try{
                    
                    System.out.println("Select row: ");
                    row = console.nextInt() - 1;
                    
                    System.out.println("Select column: ");
                    column = console.nextInt() - 1;
                }catch(InputMismatchException e){
                    System.out.println("Invalid input. Please input a valid number between 1 and 3, inclusive.");
                    console.nextLine();
                }

                if(row < 0 || row > 2 
                    || column < 0 || column > 2 
                    || board[row][column] != '-')
                {
                    validMove = false;
                }else{
                    validMove = true;
                    board[row][column] = currentPlayer;
                }
            }while(!validMove);

            System.out.println("Current player (" + currentPlayer + ") move is " + row + ", " + column);
            

    }
}
