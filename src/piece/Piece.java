package piece;

import java.util.*;

public class Piece {
    private List<char[][]> variants;

    private char[][] rotate(char[][] base) {
        int rows = base.length;
        int cols = base[0].length;

        char[][] rotatedShape = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedShape[j][rows - i - 1] = base[i][j];
            }
        }

        return rotatedShape;
    }

    private char[][] flip(char[][] base) {
        int rows = base.length;
        int cols = base[0].length;

        char[][] flippedShape = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flippedShape[i][cols - j - 1] = base[i][j];
            }
        }
        
        return flippedShape;
    }

    private boolean checkDupes (char[][] shape, Set<String> unique) {
        return unique.add(Arrays.deepToString(shape));
      
    }

    private void generateVariants(char[][] base) {
        Set<String> unique = new LinkedHashSet<>();

        char[][] currentShape = base;
        if (checkDupes(currentShape, unique)) {
            variants.add(currentShape);
        }

        for (int i = 0; i < 3; i++) {
            currentShape = rotate(currentShape);
            if (checkDupes(currentShape, unique)) {
                variants.add(currentShape);
            }
        }

        char[][] flippedShape = flip(base);
        if (checkDupes(flippedShape, unique)) {
            variants.add(flippedShape);
        }

        currentShape = flippedShape;

        for (int i = 0; i < 3; i++) {
            currentShape = rotate(currentShape);
            if (checkDupes(currentShape, unique)) {
                variants.add(currentShape);
            }
        }
    }

    private char[][] trimPiece(char[][] piece) {
        int rows = piece.length;
        int cols = piece[0].length;

        int minRows = rows, maxRows = 0, minCols = cols, maxCols = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (piece[i][j] != ' ') {
                    minRows = Math.min(minRows, i);
                    maxRows = Math.max(maxRows, i);
                    minCols = Math.min(minCols, j);
                    maxCols = Math.max(maxCols, j);
                }
            }
        }

        int newRows = maxRows - minRows + 1;
        int newCols = maxCols - minCols + 1;

        char[][] trimmedPiece = new char[newRows][newCols];
        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                trimmedPiece[i][j] = piece[minRows + i][minCols + j];
            }
        }
    
        return trimmedPiece;
    }

    public Piece(List<String> shapeStrings) {
        this.variants = new ArrayList<>();
        int rows = shapeStrings.size();
        int cols = 0;

        for(String shapeString : shapeStrings){
            cols = Math.max(cols, shapeString.length());
        }

        char[][] base = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            int lineLength = shapeStrings.get(i).length();
            for (int j = 0; j < cols; j++) {
                if (j >= lineLength) {
                    base[i][j] = ' ';
                } else {
                    base[i][j] = shapeStrings.get(i).charAt(j);
                }
            }
        }

        generateVariants(trimPiece(base));
    }

    public List<char[][]> getVariants() {
        return variants;
    }
}
