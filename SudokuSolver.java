public class SudokuSolver {
    private int[][] board;
    private static final int EMPTY = 0;
    private static final int SIZE = 9;
 
    public SudokuSolver(int[][] board) {
        this.board = new int[SIZE][SIZE];
 
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }
 
    private boolean isRowValid(int row, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
 
        return true;
    }
 
    private boolean isColValid(int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
 
        return true;
    }
 
    private boolean isBoxValid(int row, int col, int num) {
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
 
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
 
        return true;
    }
 
    private boolean isMoveValid(int row, int col, int num) {
        return isRowValid(row, num) && isColValid(col, num) && isBoxValid(row, col, num);
    }
 
    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isMoveValid(row, col, num)) {
                            board[row][col] = num;
                            if (solve()) {
                                return true;
                            } else {
                                board[row][col] = EMPTY;
                            }
                        }
                    }
 
                    return false;
                }
            }
        }
 
        return true;
    }
 
    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("-----------");
            }
 
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("|");
                }
 
                if (j == SIZE - 1) {
                    System.out.println(board[i][j]);
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
        }
    }
    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
    
        SudokuSolver solver = new SudokuSolver(board);
        System.out.println("Original board:");
        solver.printBoard();
        
        if (solver.solve()) {
            System.out.println("\nsolucion:");
            solver.printBoard();
        } else {
            System.out.println("\nNo se pudo resolver.");
        }
    }
    
    
}
