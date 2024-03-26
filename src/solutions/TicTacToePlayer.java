package solutions;

import core_algorithms.MiniMax;
import problems.Game;
import problems.TicTacToe;

import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class TicTacToePlayer extends MiniMax<char[][], int[]>
{
    Scanner input = new Scanner(System.in);
    /*while(state is not terminal)
      {
        wait for human input
        run minimax search
      }
    */


    public TicTacToePlayer(int size)
    {
        super(new TicTacToe(size, TicTacToe.Marks.X), false);
    }

    public void printBoard()
    {
        for(int i = 0; i < game.getBoard().length; i++)
        {
            for(int j = 0; j< game.getBoard()[i].length; j++ )
            {
                System.out.print("[" + game.getBoard()[j][i] + "]");
            }
            System.out.println();
        }
    }

    public void play()
    {
        do
        {
            printBoard();
            System.out.println("Enter coordinates for move ex. 0 0, 1 2\n");
            int[] move = new int[2];
            move[0] = input.nextInt();
            move[1] = input.nextInt();

            int[] aiAction = minimaxSearch(game.execute(move, game.getBoard()));
            if(aiAction != null)
            {
                game.execute(aiAction, game.getBoard());
            }
            else if(game.utility(game.getBoard()) == 1)
            {
                printBoard();
                System.out.println("You win!");
            }
            else if(game.utility(game.getBoard()) == -1)
            {
                printBoard();
                System.out.println("You lose :(");
            }
            else
            {
                printBoard();
                System.out.println("Draw!");
            }

        }while(!game.isTerminal(game.getBoard()));
    }

    public static void main(String[] args)
    {
        TicTacToePlayer player = new TicTacToePlayer(3);
        player.play();
    }


}
