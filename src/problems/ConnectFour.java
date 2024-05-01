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

        int row = findTopRow(col, board);

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


    public int utility(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Check rows for a win
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 3]) {
                    return board[i][j] == 'R' ? 1 : -1;
                }
            }
        }

        // Check columns for a win
        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]) {
                    return board[i][j] == 'R' ? 1 : -1;
                }
            }
        }

        // Check diagonals for a win (bottom-left to top-right)
        for (int i = 3; i < rows; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i - 1][j + 1] && board[i][j] == board[i - 2][j + 2] && board[i][j] == board[i - 3][j + 3]) {
                    return board[i][j] == 'R' ? 1 : -1;
                }
            }
        }

        // Check diagonals for a win (top-left to bottom-right)
        for (int i = 0; i <= rows - 4; i++) {
            for (int j = 0; j <= cols - 4; j++) {
                if (board[i][j] != ' ' && board[i][j] == board[i + 1][j + 1] && board[i][j] == board[i + 2][j + 2] && board[i][j] == board[i + 3][j + 3]) {
                    return board[i][j] == 'R' ? 1 : -1;
                }
            }
        }

        // If no winner and the board is full, return draw
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == ' ') {
                    return 0; // Game is not finished yet
                }
            }
        }

        // If no winner and the board is not full, return 0
        return 0;
    }


    public List<Integer> actions(char[][] state)
    {
        List<Integer> possibleActions = new ArrayList<>();

        // Iterate through each column to find empty slots
        for (int col = 0; col < state[0].length; col++) {
            // Check from top to bottom in the column to find the first empty slot
            for (int row = 0; row < state.length; row++) {
                if (state[row][col] == ' ') {
                    possibleActions.add(col); // Add column index to possible actions
                    break; // Move to the next column after finding the first empty slot
                }
            }
        }


        return possibleActions;
    }


    public char[][] getBoard()
    {
        return this.board;
    }


    private int findTopRow(int col, char[][] board) {
        for (int row = 0; row < board.length; row++) {
            if (board[row][col] != ' ') {
                // Found the topmost occupied row
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
