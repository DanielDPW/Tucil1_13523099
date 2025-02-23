import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import parser.Parser;
import board.Board;
import solver.Solver;
import image.ImageGenerator;
import java.util.List;

public class MainGUI {
    private JFrame frame;
    private JTextArea fileContentArea;
    private JTextArea resultTextArea;
    private JLabel imageLabel;
    private JButton saveTxtButton;
    private JButton savePngButton;
    private File selectedFile;
    private Board board;
    private Solver solver;
    private BufferedImage generatedImage;

    public MainGUI() {
        frame = new JFrame("IQ Puzzle Pro Solver - 13523099");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        JButton uploadButton = new JButton("Upload File");
        fileContentArea = new JTextArea(10, 20);
        fileContentArea.setEditable(false);
        JScrollPane fileScrollPane = new JScrollPane(fileContentArea);
        leftPanel.add(fileScrollPane, BorderLayout.CENTER);
        leftPanel.add(uploadButton, BorderLayout.SOUTH);
        new DropTarget(fileContentArea, new FileDropHandler());

        JPanel centerPanel = new JPanel(new BorderLayout());
        imageLabel = new JLabel("", SwingConstants.CENTER);
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultTextArea = new JTextArea("The result will be displayed here");
        resultTextArea.setEditable(false);
        resultPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        JButton runButton = new JButton("Solve");
        saveTxtButton = new JButton("Save as TXT");
        savePngButton = new JButton("Save as PNG");
        saveTxtButton.setEnabled(false);
        savePngButton.setEnabled(false);

        buttonPanel.add(runButton);
        buttonPanel.add(saveTxtButton);
        buttonPanel.add(savePngButton);

        rightPanel.add(resultPanel, BorderLayout.NORTH);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(rightPanel, BorderLayout.EAST);

        uploadButton.addActionListener(_ -> uploadFile());
        runButton.addActionListener(_ -> runSolver());
        saveTxtButton.addActionListener(_ -> saveAsText());
        savePngButton.addActionListener(_ -> saveAsImage());

        frame.setVisible(true);
    }

    private void uploadFile() {
        JFileChooser fileChooser = new JFileChooser(new File("../input"));
        fileChooser.setDialogTitle("Select Input File");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));

        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            handleFileSelection(fileChooser.getSelectedFile());
        }
    }

    private void handleFileSelection(File file) {
        if (file == null || !file.getName().endsWith(".txt")) {
            fileContentArea.setText("Invalid file. Please upload a .txt file.");
            return;
        }

        selectedFile = file;
        fileContentArea.setText("");
        resultTextArea.setText("The result will be displayed here");
        imageLabel.setIcon(null);
        saveTxtButton.setEnabled(false);
        savePngButton.setEnabled(false);

        try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileContentArea.append(line + "\n");
            }
        } catch (IOException e) {
            fileContentArea.setText("Failed to read file.");
        }
    }

    private void runSolver() {
        if (selectedFile == null) {
            resultTextArea.setText("Upload a file first.");
            return;
        }

        try {
            board = Parser.parseFile(selectedFile.getAbsolutePath());
            solver = new Solver(board);
            boolean solution = solver.solve(0);

            if (solution) {
                resultTextArea.setText("Solution found:\nExecution time: " + solver.getExecutionTime() + " ms\nCases iterated: " + solver.getIterationCount());
            } else {
                resultTextArea.setText("No solution found:\nExecution time: " + solver.getExecutionTime() + " ms\nCases iterated: " + solver.getIterationCount());
                board.resetGrid();
            }

            generateImage();
            displayImage();
            saveTxtButton.setEnabled(true);
            savePngButton.setEnabled(true);
            
        } catch (Exception e) {
            resultTextArea.setText("Error: " + e.getMessage());
        }
    }

    private void generateImage() {
        try {
            ImageGenerator imageGenerator = new ImageGenerator(board.getGrid());
            generatedImage = imageGenerator.generateImage(null);
        } catch (Exception e) {
            resultTextArea.setText("Failed to generate image.");
        }
    }

    private void displayImage() {
        if (generatedImage != null) {
            imageLabel.setIcon(new ImageIcon(generatedImage));
        }
    }

    private void saveAsText() {
        JFileChooser fileChooser = new JFileChooser(new File("../test"));
        fileChooser.setDialogTitle("Save as TXT");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text Files", "txt"));
    
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile() + ".txt");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
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
    
                bw.write("Execution time: " + solver.getExecutionTime() + " ms");
                bw.newLine();
                bw.write("Cases iterated: " + solver.getIterationCount());
    
                resultTextArea.setText("The solution is saved at: " + file.getAbsolutePath());
            } catch (IOException e) {
                resultTextArea.setText("Failed to save the solution.");
            }
        }
    }
    
    private void saveAsImage() {
        if (generatedImage == null) {
            resultTextArea.setText("No image to save.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser(new File("../test"));
        fileChooser.setDialogTitle("Save as PNG");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Images", "png"));
    
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File outputImage = new File(fileChooser.getSelectedFile() + ".png");
            
            try {
                ImageIO.write(generatedImage, "png", outputImage);
                resultTextArea.setText("The image is saved at: " + outputImage.getAbsolutePath());
            } catch (IOException e) {
                resultTextArea.setText("Failed to save the image.");
            }
        }
    }

    private class FileDropHandler extends DropTargetAdapter {
        @Override
        public void drop(DropTargetDropEvent event) {
            try {
                event.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable transferable = event.getTransferable();
                Object data = transferable.getTransferData(DataFlavor.javaFileListFlavor);
                if (data instanceof List<?>) {
                    List<?> genericList = (List<?>) data;
                    if (!genericList.isEmpty() && genericList.get(0) instanceof File) {
                        handleFileSelection((File) genericList.get(0));
                    }
                }
            } catch (Exception ex) {
                fileContentArea.setText("Drag and drop failed.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}