import java.io.*;
import java.util.*;
import parser.Parser;
import board.Board;
import solver.Solver;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <file>");
            return;
        }

        String filename = args[0];
        
        try {
            Board board = Parser.parseFile(filename);
            Solver solver = new Solver(board);
            boolean solution = solver.solve(0);

            if (solution) {
                System.out.println("Solution found:");
                board.displayBoard();
            } else {
                System.out.println("No solution found:");
                board.resetGrid();
                board.displayBoard();
            }
            System.out.println("Search time: " + solver.getExecutionTime() + " ms");
            System.out.println("Cases considered: " + solver.getIterationCount());

            Scanner scanner = new Scanner(System.in);
            String ans;
            
            while (true) {
                System.out.print("Would you like to save the solution? (yes or no): ");
                ans = scanner.nextLine().trim().toLowerCase();

                if (ans.equals("yes") || ans.equals("no")) {
                    break;
                }
                System.out.println("Please enter 'yes' or 'no'.");
            }
            
            if (ans.equals("yes")) {
                String outputFileName;

                while (true) {
                    System.out.print("Enter the filename: ");
                    outputFileName = scanner.nextLine().trim();

                    if (isValidFilename(outputFileName)) {
                        break;
                    }
                    System.out.println("Avoid the following characters: \\ / : * ? \" < > |");
                }

                File testDirectory = new File("../test");
                if (!testDirectory.exists()) {
                    testDirectory.mkdir();
                }

                File outputText = new File(testDirectory, outputFileName + ".txt");
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputText))) {
                    for (int i = 0; i < board.getRows(); i++) {
                        for (int j = 0; j < board.getCols(); j++) {
                            if (board.getElmt(i, j) == ' ') {
                                bw.write('.');
                            } else if (board.getElmt(i, j) == '.') {
                                bw.write('?');
                            } else {
                                bw.write(board.getElmt(i, j));
                            }
                        }
                        bw.newLine();
                    }
                    bw.write("Search time: " + solver.getExecutionTime() + " ms");
                    bw.newLine();
                    bw.write("Cases considered: " + solver.getIterationCount());

                    System.out.println("The solution is saved at " + outputText.getAbsolutePath());
                }  catch (IOException e) {
                    System.out.println("Failed to save the solution.");
                }
            }

            scanner.close();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static boolean isValidFilename(String filename) {
        return filename.matches("^[^\\\\/:*?\"<>|]+$");
    }
}
