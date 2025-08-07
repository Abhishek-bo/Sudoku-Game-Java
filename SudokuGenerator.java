import java.util.*;

public class SudokuGenerator {
    private int[][] board;
    private int[][] solution;

    public SudokuGenerator() {
        board = new int[9][9];
        generateBoard();
        solution = copyBoard(board);
        removeNumbers(40); // Change for difficulty
    }

    public int[][] getPuzzle() {
        return board;
    }

    public int[][] getSolution() {
        return solution;
    }

    private void generateBoard() {
        fillBoard(board,0, 0);
    }
    private static boolean fillBoard(int [][]board,int row,int col) {
        //base case
        if (row == 9) {
            return true;
        }
        //work
        int nextrow = row, nextcol = col + 1;
        if (col + 1 == 9) {
            nextrow = row + 1;
            nextcol = 0;
        }
        if (board[row][col] != 0) {
            return fillBoard(board, nextrow, nextcol);
        }

        //recussion
        for (int digit = 1; digit < 10; digit++) {
            if (isSafe( board,row, col, digit)) {
                board[row][col] = digit;
                if (fillBoard(board, nextrow, nextcol)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private static boolean isSafe(int [][]board,int row, int col, int num) {

        //check column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        //check row
            for (int i = 0; i < 9; i++) {
               if(board[i][col] == num){
                   return false;
               }
        }
     // check int grid
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private void removeNumbers(int count) {
        Random rand = new Random();
        while (count > 0) {
            int row = rand.nextInt(9);
            int col = rand.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                count--;
            }
        }
    }

    private int[][] copyBoard(int[][] original) {
        int[][] copy = new int[9][9];
        for (int i = 0; i < 9; i++) {
            copy[i] = Arrays.copyOf(original[i], 9);
        }
        return copy;
    }
}

