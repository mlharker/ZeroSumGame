package solutions;

import core_algorithms.MiniMax;
import problems.ConnectFour;

import java.util.Scanner;

public class ConnectFourPlayer extends MiniMax<char[][], Integer>
{
    Scanner input = new Scanner(System.in);

    public ConnectFourPlayer()
    {
        super(new ConnectFour(ConnectFour.Marks.R), true);
    }

    public void printBoard()
    {
        for(int i = 0; i < game.getBoard().length; i++)
        {
            for(int j = 0; j< game.getBoard()[i].length; j++ )
            {
                System.out.print("[" + game.getBoard()[i][j] + "]");
            }
            System.out.println();
        }
    }

    public void play()
    {
        do
        {
            printBoard();
            System.out.println("Enter a column to drop your piece\n");
            Integer move = input.nextInt();

            char[][] updatedBoard = game.execute(move, game.getBoard());
            if (game.isTerminal(updatedBoard))
            {
                break;
            }
            Integer aiAction = minimaxSearch(updatedBoard);
            updatedBoard = game.execute(aiAction, updatedBoard);
        }
        while(!game.isTerminal(game.getBoard()));

        if(game.utility(game.getBoard()) == 1)
        {
            printBoard();
            System.out.println("You lose :(");
        }
        else if(game.utility(game.getBoard()) == -1)
        {
            printBoard();
            System.out.println("You win!");
        }
        else
        {
            printBoard();
            System.out.println("Draw!");
        }
    }


    public static void main(String[] args)
    {
        ConnectFourPlayer player = new ConnectFourPlayer();
        player.play();
    }
}
