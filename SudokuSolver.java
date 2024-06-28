public class SudokuSolver {

    public static boolean solveSudoku(int[][] grid) {
        int N = grid.length;

        // Find the first empty cell in the grid
        boolean isEmpty = true;
        int row = -1;
        int col = -1;

        for (int i = 0; i < N && isEmpty; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
        }

        // If no empty cell found, puzzle is solved
        if (isEmpty) {
            return true;
        }

        // Try numbers 1 to 9 in the empty cell
        for (int num = 1; num <= 9; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudoku(grid)) {
                    return true;
                }

                // Backtrack
                grid[row][col] = 0;
            }
        }

        // No solution found
        return false;
    }

    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check if num is not already present in current row, current column, and current 3x3 subgrid
        return !usedInRow(grid, row, num) &&
                !usedInColumn(grid, col, num) &&
                !usedInBox(grid, row - row % 3, col - col % 3, num);
    }

    private static boolean usedInRow(int[][] grid, int row, int num) {
        for (int col = 0; col < grid.length; col++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInColumn(int[][] grid, int col, int num) {
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInBox(int[][] grid, int boxStartRow, int boxStartCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (grid[row + boxStartRow][col + boxStartCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printGrid(int[][] grid) {
        int N = grid.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
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

        System.out.println("Sudoku grid before solving:");
        printGrid(grid);

        if (solveSudoku(grid)) {
            System.out.println("\nSudoku grid after solving:");
            printGrid(grid);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }
}
