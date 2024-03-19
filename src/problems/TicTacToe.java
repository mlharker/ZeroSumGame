package problems;

public class TicTacToe implements Game<char[][], int[]>{
    // e.g., 3 means a 3x3 board
    private final int BOARD_SIZE;
    private final char[][] board;
    private final boolean[][] marked;
    private Marks turn;

    public enum Marks{X,O}

    public TicTacToe(int size, Marks turn){
        this.BOARD_SIZE = size;
        this.board = new char[size][size];
        this.marked = new boolean[size][size];
        for (int row=0; row<size; row++){
            for(int col=0; col<size; col++){
                board[row][col] = ' ';
                marked[row][col] = false;
            }
        }
    }

    public boolean isTerminal(char[][] board){
        for(int row=0; row<BOARD_SIZE; row++){
            for(int col=0; col<BOARD_SIZE; col++){
                if(!marked[row][col]){
                    return false;
                }
            }
        }
    }

    public char[][] execute(int[] position, char[][] board){
        if(turn == Marks.X){
            board[position[0]][position[1]] = Marks.X.toString().charAt(0);
        }else{
            board[position[0]][position[1]] = Marks.O.toString().charAt(0);
        }
        marked[position[0]][position[1]] = true;
        switchTurn();
        return board;
    }

    public char[][] undo(int[] position, char[][] board){
        board[position[0]][position[1]] = ' ';
        marked[position[0]][position[1]] = false;
        switchTurn();
        return board;
    }

    public void switchTurn(){
        if(turn == Marks.X){
            turn = Marks.O;
        }else{
            turn = Marks.X;
        }
    }

}
