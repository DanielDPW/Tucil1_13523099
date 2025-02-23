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
        put('A', "\033[38;2;255;0;0m");      // Red (255, 0, 0)
        put('B', "\033[38;2;0;255;0m");      // Green (0, 255, 0)
        put('C', "\033[38;2;0;0;255m");      // Blue (0, 0, 255)
        put('D', "\033[38;2;255;255;0m");    // Yellow (255, 255, 0)
        put('E', "\033[38;2;255;0;255m");    // Magenta (255, 0, 255)
        put('F', "\033[38;2;0;255;255m");    // Cyan (0, 255, 255)
        put('G', "\033[38;2;128;0;0m");      // Dark Red (128, 0, 0)
        put('H', "\033[38;2;0;128;0m");      // Dark Green (0, 128, 0)
        put('I', "\033[38;2;0;0;128m");      // Dark Blue (0, 0, 128)
        put('J', "\033[38;2;128;128;0m");    // Olive (128, 128, 0)
        put('K', "\033[38;2;128;0;128m");    // Purple (128, 0, 128)
        put('L', "\033[38;2;0;128;128m");    // Teal (0, 128, 128)
        put('M', "\033[38;2;255;165;0m");    // Orange (255, 165, 0)
        put('N', "\033[38;2;75;0;130m");     // Indigo (75, 0, 130)
        put('O', "\033[38;2;139;69;19m");    // Saddle Brown (139, 69, 19)
        put('P', "\033[38;2;255;192;203m");  // Pink (255, 192, 203)
        put('Q', "\033[38;2;173;216;230m");  // Light Blue (173, 216, 230)
        put('R', "\033[38;2;0;100;0m");      // Dark Green (0, 100, 0)
        put('S', "\033[38;2;165;42;42m");    // Brown (165, 42, 42)
        put('T', "\033[38;2;240;230;140m");  // Khaki (240, 230, 140)
        put('U', "\033[38;2;46;139;87m");    // Sea Green (46, 139, 87)
        put('V', "\033[38;2;255;215;0m");    // Gold (255, 215, 0)
        put('W', "\033[38;2;0;191;255m");    // Deep Sky Blue (0, 191, 255)
        put('X', "\033[38;2;138;43;226m");   // Blue Violet (138, 43, 226)
        put('Y', "\033[38;2;127;255;212m");  // Aquamarine (127, 255, 212)
        put('Z', "\033[38;2;218;112;214m");  // Orchid (218, 112, 214)
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

    public char[][] getGrid() {
        return grid;
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
