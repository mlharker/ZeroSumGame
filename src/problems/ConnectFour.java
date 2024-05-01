package problems;

import java.util.ArrayList;
import java.util.List;

public class ConnectFour implements Game<char[][], Integer>
{
    private final char[][] board;
    private final boolean[][] marked;
    private Marks turn;

    public enum Marks{R,B}

    public ConnectFour(Marks turn)
    {
        this.board = new char[4][5];
        this.marked = new boolean[4][5];
        this.turn = turn;
        for (int row = 0; row < 4; row++){
            for(int col = 0; col < 5; col++){
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

        for(int row=0; row<4; row++)
        {
            for(int col=0; col<5; col++)
            {
                if(!marked[row][col])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] execute(Integer col, char[][] board)
    {
        int row = getNextAvailableRow(col, board);
        if(turn == Marks.R)
        {
            board[row][col] = Marks.R.toString().charAt(0);
        }
        else
        {
            board[row][col] = Marks.B.toString().charAt(0);
        }


        marked[row][col] = true;
        switchTurn();
        return board;
    }


    public char[][] undo(Integer col, char[][] board)
    {
        if (col < 0 || col >= board[0].length)
        {
            return board;
        }

        int row = findLowestOccupiedRow(col, board);

        if (row != -1) {
            board[row][col] = ' ';
            marked[row][col] = false;
            switchTurn();
        }
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


    public int utility(char[][] board)
    {
        //check rows
        for(int row=0; row<4; row++)
        {
            int rowSum = 0;
            for(int col=0; col<5; col++)
            {
                if(board[row][col] == Marks.R.toString().charAt(0))
                {
                    rowSum++;
                }
                else if(board[row][col] == Marks.B.toString().charAt(0))
                {
                    rowSum--;
                }
            }

            if(rowSum == 4)
            {
                return 1;
            }
            else if(rowSum == -4)
            {
                return -1;
            }
        }


        //check columns
        for(int col=0; col<4; col++)
        {
            int colSum = 0;
            for(int row=0; row<4; row++)
            {
                if(board[row][col] == Marks.R.toString().charAt(0))
                {
                    colSum++;
                }
                else if(board[row][col] == Marks.B.toString().charAt(0))
                {
                    colSum--;
                }
            }

            if(colSum == 4)
            {
                return 1;
            }
            else if(colSum == -4)
            {
                return -1;
            }
        }


        int diaSum = 0;
        //check diagonal
        for (int row = 0; row <= 0; row++)
        {
            for (int col = 0; col <= 1; col++)
            {
                diaSum = 0;
                for (int d = 0; d < 4; d++)
                {
                    if (board[row + d][col + d] == Marks.R.toString().charAt(0))
                    {
                        diaSum++;
                    }
                    else if (board[row + d][col + d] == Marks.B.toString().charAt(0))
                    {
                        diaSum--;
                    }
                }
            }
        }

        if(diaSum == 4)
        {
            return 1;
        }
        if(diaSum == -4)
        {
            return -1;
        }


        diaSum = 0;
        for (int row = 0; row <= 0; row++)
        {
            for (int col = 4; col < 5; col++)
            {
                diaSum = 0;
                for (int d = 0; d < 4; d++)
                {
                    if (board[row + d][col - d] == Marks.R.toString().charAt(0))
                    {
                        diaSum++;
                    }
                    else if (board[row + d][col - d] == Marks.B.toString().charAt(0))
                    {
                        diaSum--;
                    }
                }
            }
        }

        if(diaSum == 4)
        {
            return 1;
        }
        else if(diaSum == -4)
        {
            return -1;
        }

        return 0;
    }


    public List<Integer> actions(char[][] state)
    {
        List<Integer> possibleActions = new ArrayList<>();

        // Iterate through each column to find empty slots
        for (int col = 0; col < state[0].length; col++) {
            if (state[0][col] == ' ') {
                possibleActions.add(col); // Add column index to possible actions
            }
        }

        return possibleActions;
    }


    public char[][] getBoard()
    {
        return this.board;
    }


    private int findLowestOccupiedRow(int col, char[][] board) {
        // Iterate through the specified column from bottom to top
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] != ' ') {
                // Found the lowest occupied row
                return row;
            }
        }
        // Column is empty
        return -1;
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
}
