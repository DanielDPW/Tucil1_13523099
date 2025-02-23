package image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class ImageGenerator {
    private char[][] grid;

    private static Map<Character, Color> colorMap = new HashMap<>() {{
        put(' ', new Color(255, 255, 255));   // White (255, 255, 255)
        put('.', new Color(0, 0, 0));         // Black (0, 0, 0)
        put('A', new Color(255, 0, 0));       // Red (255, 0, 0)
        put('B', new Color(0, 255, 0));       // Green (0, 255, 0)
        put('C', new Color(0, 0, 255));       // Blue (0, 0, 255)
        put('D', new Color(255, 255, 0));     // Yellow (255, 255, 0)
        put('E', new Color(255, 0, 255));     // Magenta (255, 0, 255)
        put('F', new Color(0, 255, 255));     // Cyan (0, 255, 255)
        put('G', new Color(128, 0, 0));       // Dark Red (128, 0, 0)
        put('H', new Color(0, 128, 0));       // Dark Green (0, 128, 0)
        put('I', new Color(0, 0, 128));       // Dark Blue (0, 0, 128)
        put('J', new Color(128, 128, 0));     // Olive (128, 128, 0)
        put('K', new Color(128, 0, 128));     // Purple (128, 0, 128)
        put('L', new Color(0, 128, 128));     // Teal (0, 128, 128)
        put('M', new Color(255, 165, 0));     // Orange (255, 165, 0)
        put('N', new Color(75, 0, 130));      // Indigo (75, 0, 130)
        put('O', new Color(139, 69, 19));     // Saddle Brown (139, 69, 19)
        put('P', new Color(255, 192, 203));   // Pink (255, 192, 203)
        put('Q', new Color(173, 216, 230));   // Light Blue (173, 216, 230)
        put('R', new Color(0, 100, 0));       // Dark Green (0, 100, 0)
        put('S', new Color(165, 42, 42));     // Brown (165, 42, 42)
        put('T', new Color(240, 230, 140));   // Khaki (240, 230, 140)
        put('U', new Color(46, 139, 87));     // Sea Green (46, 139, 87)
        put('V', new Color(255, 215, 0));     // Gold (255, 215, 0)
        put('W', new Color(0, 191, 255));     // Deep Sky Blue (0, 191, 255)
        put('X', new Color(138, 43, 226));    // Blue Violet (138, 43, 226)
        put('Y', new Color(127, 255, 212));   // Aquamarine (127, 255, 212)
        put('Z', new Color(218, 112, 214));   // Orchid (218, 112, 214)
    }};    

    public BufferedImage generateImage(File file) {
        int cellSize = 64;
        int rows = grid.length;
        int cols = grid[0].length;
        int height = rows * cellSize;
        int width = cols * cellSize;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char elmt = grid[i][j];
                graphics.setColor(colorMap.getOrDefault(elmt, Color.WHITE));
                graphics.fillRect(cellSize * j, cellSize * i, cellSize, cellSize);
                
                if (elmt != ' ' && elmt != '.') {
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(cellSize * j, cellSize * i, cellSize, cellSize);
            
                    graphics.setFont(new Font("Monospaced", Font.BOLD, 20));
                    FontMetrics metrics = graphics.getFontMetrics();
                    
                    int textWidth = metrics.stringWidth(String.valueOf(elmt));
                    int textHeight = metrics.getAscent();
        
                    int x = (cellSize * j) + (cellSize - textWidth) / 2;
                    int y = (cellSize * i) + ((cellSize - textHeight) / 2) + textHeight;
        
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(String.valueOf(elmt), x, y);
                }
                
            }
        }

        graphics.dispose();

        return image;
    }
    public ImageGenerator(char[][] grid) {
        this.grid = grid;
    }
}
