package board;

import java.util.*;
import piece.Piece;

public class Board {
    private int rows;
    private int cols;
    private char[][] originalGrid;
    private char[][] grid;
    private List<Piece> pieces;
    private int pieceCount;

    private static Map<Character, String> colorMap = new HashMap<>() {{
        put('A', "\033[38;5;1m");   // Red (128, 0, 0)
        put('B', "\033[38;5;2m");   // Green (0, 128, 0)
        put('C', "\033[38;5;3m");   // Yellow (128, 128, 0)
        put('D', "\033[38;5;4m");   // Blue (0, 0, 128)
        put('E', "\033[38;5;5m");   // Magenta (128, 0, 128)
        put('F', "\033[38;5;6m");   // Cyan (0, 128, 128)
        put('G', "\033[38;5;9m");   // Bright Red (255, 0, 0)
        put('H', "\033[38;5;10m");  // Bright Green (0, 255, 0)
        put('I', "\033[38;5;11m");  // Bright Yellow (255, 255, 0)
        put('J', "\033[38;5;12m");  // Bright Blue (0, 0, 255)
        put('K', "\033[38;5;13m");  // Bright Magenta (255, 0, 255)
        put('L', "\033[38;5;14m");  // Bright Cyan (0, 255, 255)
        put('M', "\033[38;5;208m"); // Orange (255, 102, 0)
        put('N', "\033[38;5;120m"); // Light Green (102, 255, 102)
        put('O', "\033[38;5;37m");  // Teal (0, 204, 204)
        put('P', "\033[38;5;183m"); // Lavender (204, 153, 255)
        put('Q', "\033[38;5;178m"); // Gold (204, 153, 0)
        put('R', "\033[38;5;213m"); // Pink (255, 153, 204)
        put('S', "\033[38;5;19m");  // Deep Blue (0, 51, 204)
        put('T', "\033[38;5;52m");  // Maroon (102, 0, 0)
        put('U', "\033[38;5;54m");  // Indigo (75, 0, 130)
        put('V', "\033[38;5;166m"); // Dark Orange (204, 85, 0)
        put('W', "\033[38;5;141m"); // Light Purple (153, 102, 204)
        put('X', "\033[38;5;45m");  // Turquoise (0, 153, 153)
        put('Y', "\033[38;5;130m"); // Light Brown (153, 102, 51)
        put('Z', "\033[38;5;39m");  // Light Blue (0, 153, 255)
    }};

    public Board(char[][] board, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.originalGrid = new char[rows][cols];
        this.grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.originalGrid[i][j] = board[i][j];
                this.grid[i][j] = board[i][j];
            }
        }
        this.pieces = null;
    }

    public void setPieces(List<Piece> pieces, int P) {
        this.pieces = pieces;
        this.pieceCount = P;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char getElmt(int r, int c) {
        return grid[r][c];
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setElmt(int r, int c, char x) {
        grid[r][c] = x;
    }

    public boolean isGridFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (getElmt(i, j) == ' ') {
                    System.out.print('.');
                } else if (getElmt(i, j) == '.') {
                    System.out.print('?');
                } else {
                    char elmt = getElmt(i, j);
                    String color = colorMap.getOrDefault(elmt, "");
                    System.out.print(color + elmt + "\033[0m");
                    
                }
            }
            System.out.println();
        }
    }

    public void resetGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = originalGrid[i][j];
            }
        }
    }
}
