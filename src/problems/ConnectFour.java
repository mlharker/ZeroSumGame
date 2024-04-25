package problems;

import java.util.List;

public class ConnectFour implements Game<char[][], Integer>
{
    private final char[][] board;
    private final boolean[][] marked;
    private Marks turn;

    public enum Marks{R,B}

    public ConnectFour(Marks turn)
    {
        this.board = new char[6][7];
        this.marked = new boolean[6][7];
        this.turn = turn;
        for (int row = 0; row < 6; row++){
            for(int col = 0; col < 7; col++){
                board[row][col] = ' ';
                marked[row][col] = false;
            }
        }
    }

    public boolean isTerminal(char[][] board){
        int utilityVal = utility(board);

        if(utilityVal == 1 || utilityVal == -1)
        {
            return true;
        }

        for(int row=0; row<6; row++)
        {
            for(int col=0; col<7; col++)
            {
                if(!marked[row][col])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] execute(int col, char[][] board)
    {
        int row = getNextAvailableRow(col, board);
        if(turn == Marks.R)
        {
            board[col][row] = Marks.R.toString().charAt(0);
        }
        else
        {
            board[col][row] = Marks.B.toString().charAt(0);
        }

        marked[col][board[0].length] = true;
        switchTurn();
        return board;
    }

    private int getNextAvailableRow(int col, char[][] board)
    {
        for (int row = board.length - 1; row >= 0; row--)
        {
            if (board[row][col] == ' ')
            {
                return row;
            }
        }
        return -1; // Column is full
    }


    public char[][] undo(int[] position, char[][] board)
    {
        board[position[0]][position[1]] = ' ';
        marked[position[0]][position[1]] = false;
        switchTurn();
        return board;
    }

    public void switchTurn(){
        if(turn == Marks.R)
        {
            turn = Marks.B;
        }
        else
        {
            turn = Marks.R;
        }
    }





}
