package parser;

import java.util.*;
import java.io.*;
import board.Board;
import piece.Piece;

public class Parser {
    public static Board parseFile(String filename) {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            do {
                line = br.readLine();
                if (line == null) {
                    throw new IllegalArgumentException("File does not contain valid board dimensions and/or piece numbers.");
                }
                line = line.trim();
            } while (line.isEmpty());

            String[] firstLine = line.split("\\s+");
            if (firstLine.length != 3) {
                throw new IllegalArgumentException("First line must have exactly 3 arguments (N, M and P).");
            }
            
            int N = Integer.parseInt(firstLine[0]);
            int M = Integer.parseInt(firstLine[1]);
            int P = Integer.parseInt(firstLine[2]);

            if (N <= 0 || M <= 0) {
                throw new IllegalArgumentException("Board size (N x M) must be positive nonzero integers.");
            }

            if (P < 1 || P > 26) {
                throw new IllegalArgumentException("P must be between 1 and 26.");
            }

            do {
                line = br.readLine();
                if (line == null) {
                    throw new IllegalArgumentException("File does not contain a valid board type.");
                }
                line = line.trim();
            } while (line.isEmpty());

            String boardType = line;
            if (!boardType.equals("CUSTOM") && !boardType.equals("DEFAULT")) {
                throw new IllegalArgumentException("Board type must be 'DEFAULT' or 'CUSTOM'.");
            }
            
            char[][] grid = new char[N][M];
            if (boardType.equals("CUSTOM")) {
                do {
                    line = br.readLine();
                    if (line == null) {
                        throw new IllegalArgumentException("File does not contain a valid custom board.");
                    }
                    line = line.trim();
                } while (line.isEmpty());

                if (line == null || line.length() != M) {
                    throw new IllegalArgumentException("Invalid CUSTOM board.");
                }

                for (char c : line.toCharArray()) {
                    if (c != '.' && c != 'X') {
                        throw new IllegalArgumentException("Invalid CUSTOM board.");
                    }
                }
                
                char[] temp = line.toCharArray();
                for (int i = 0; i < M; i++) {
                    if (temp[i] == 'X') {
                        grid[0][i] = '.';
                    } else {
                        grid[0][i] = ' ';
                    }
                }
                for (int i = 1; i < N; i++) {
                    line = br.readLine();
                    if (line == null || line.length() != M) {
                        throw new IllegalArgumentException("Invalid CUSTOM board.");
                    }

                    for (char c : line.toCharArray()) {
                        if (c != '.' && c != 'X') {
                            throw new IllegalArgumentException("Invalid CUSTOM board.");
                        }
                    }
                    
                    temp = line.toCharArray();
                    for (int j = 0; j < M; j++) {
                        if (temp[j] == 'X') {
                            grid[i][j] = '.';
                        } else {
                            grid[i][j] = ' ';
                        }
                    }
                }
            } else {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        grid[i][j] = '.';
                    }
                }
            }
            
            Map<Character, List<String>> pieceMap = new LinkedHashMap<>();
            char prevPiece = ' ';
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                char firstChar = ' ';
                for (char c : line.toCharArray()) {
                    if (Character.isLetter(c)) {
                        firstChar = c;
                        break;
                    }
                }
                if (firstChar == ' ' || firstChar < 'A' || firstChar > 'Z') {
                    throw new IllegalArgumentException("Each pieces must be within A-Z.");
                }

                for (char c : line.toCharArray()) {
                    if (c != firstChar && c != ' ') {
                        throw new IllegalArgumentException("Invalid piece found.");
                    }
                }

                if (prevPiece != firstChar && pieceMap.containsKey(firstChar)) {
                    throw new IllegalArgumentException("Each piece must be contiguous.");
                } 

                pieceMap.putIfAbsent(firstChar, new ArrayList<>());
                pieceMap.get(firstChar).add(line);

                prevPiece = firstChar;
            }
            
            if (pieceMap.size() != P) {
                throw new IllegalArgumentException("The number of pieces must be equal to " + P + ".");
            }

            List<Piece> pieces = new ArrayList<>();
            for (List<String> piece : pieceMap.values()) {
                pieces.add(new Piece(piece));
            }

            Board board = new Board(grid, N, M);
            board.setPieces(pieces, P);
            return board;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
