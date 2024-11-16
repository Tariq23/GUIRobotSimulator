package RobotSim;

/**
 * Class representing a console canvas to display the arena and robots.
 * The canvas is a 2D array of characters.
 */
public class ConsoleCanvas {
    private char[][] canvas;
    private int width;
    private int height;
    private String studentNumber;

    /**
     * Constructor to create a canvas of a specific size.
     * @param width - The width of the canvas.
     * @param height - The height of the canvas.
     * @param studentNumber - The student number to display at the top of the canvas.
     */
    public ConsoleCanvas(int width, int height, String studentNumber) {
        this.width = width + 2;
        this.height = height + 2;
        this.studentNumber = studentNumber;
        this.canvas = new char[this.height][this.width];
        initialiseCanvas();
    }

    /**
     * Initialises the canvas by filling it with borders and empty spaces.
     */
    private void initialiseCanvas() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    canvas[i][j] = '#';
                } else {
                    canvas[i][j] = ' ';
                }
            }
        }
        addStudentNumberToBorder();
    }

    /**
     * Adds the student number to the top border of the canvas.
     */
    private void addStudentNumberToBorder() {
        int start = Math.max(1, (width - studentNumber.length()) / 2);
        for (int i = 0; i < studentNumber.length() && start + i < width - 1; i++) {
            canvas[0][start + i] = studentNumber.charAt(i);
        }
    }

    /**
     * Places a character on the canvas at the specified position.
     * @param x - The x-coordinate where the character should be placed.
     * @param y - The y-coordinate where the character should be placed.
     * @param symbol - The character representing the robot.
     */
    public void showIt(int x, int y, char symbol) {
        if (x >= 0 && x < width - 2 && y >= 0 && y < height - 2) {
            canvas[y + 1][x + 1] = symbol; // Adjust for border
        } else {
            System.out.println("Error: Coordinates out of bounds.");
        }
    }

    /**
     * Converts the canvas to a string representation.
     * @return - A string representing the canvas with all elements.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(canvas[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Gets the width of the canvas.
     * @return - The width of the canvas excluding borders.
     */
    public int getCanvasWidth() {
        return width - 2;
    }

    /**
     * Gets the height of the canvas.
     * @return - The height of the canvas excluding borders.
     */
    public int getCanvasHeight() {
        return height - 2;
    }
}
