package solver;

import board.Board;
import piece.Piece;

public class Solver {
    private Board board;
    private long iterationCount;
    private double executionTime;
    private boolean earlyStop;

    private boolean canPlacePiece(int r, int c, char[][] variant) {
        int rows = variant.length;
        int cols = variant[0].length;

        if (r + rows > board.getRows() || c + cols > board.getCols()) {
            return false;
        }

        for (int i = r; i < r + rows; i++) {
            for (int j = c; j < c + cols; j++) {
                if (variant[i - r][j - c] != ' ' && board.getElmt(i, j) != '.') {
                    return false;
                }
            }
        }

        return true;
    }

    private void placePiece(int r, int c, char[][] variant) {
        int rows = variant.length;
        int cols = variant[0].length;

        for (int i = r; i < r + rows; i++) {
            for (int j = c; j < c + cols; j++) {
                if (variant[i - r][j - c] != ' ') {
                    board.setElmt(i, j, variant[i - r][j - c]);
                }
            }
        }
    }

    private void removePiece(int r, int c, char[][] variant) {
        int rows = variant.length;
        int cols = variant[0].length;

        for (int i = r; i < r + rows; i++) {
            for (int j = c; j < c + cols; j++) {
                if (variant[i - r][j - c] != ' ') {
                    board.setElmt(i, j, '.');
                }
            }
        }
    }

    public boolean findSolution(int idx) {
        if (earlyStop) {
            return false;
        }
        
        if (board.isGridFull() && idx < board.getPieceCount()) {
            earlyStop = true;
            return false;
        }
        if (idx >= board.getPieceCount()) {
            return board.isGridFull();
        }

        Piece currentPiece = board.getPieces().get(idx);
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                if (earlyStop) {
                    return false;
                }
                for (char[][] currentVariant : currentPiece.getVariants()) {
                    iterationCount++;
                    if (canPlacePiece(i, j, currentVariant)) {
                        placePiece(i, j, currentVariant);
                        if (findSolution(idx + 1)) {
                            return true;
                        }
                        removePiece(i, j, currentVariant);
                    }
                }
            }
        }
        return false;
    }

    public boolean solve(int idx) {
        long startTime = System.nanoTime();
        boolean result = findSolution(idx);
        long endTime = System.nanoTime();
        executionTime = (endTime - startTime) / 1_000_000.0;

        return result;
    }

    public long getIterationCount() {
        return iterationCount;
    }

    public double getExecutionTime() {
        return executionTime;
    }

    public Solver(Board board) {
        this.board = board;
        this.iterationCount = 0;
        this.executionTime = 0;
        this.earlyStop = false;
    }
    
}
