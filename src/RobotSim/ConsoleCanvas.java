package RobotSim;

/**
 * Class representing a console canvas to display the arena and robots.
 * The canvas is a 2D array of characters.
 */
public class ConsoleCanvas {
    private char[][] canvas; // 2D array for the arena
    private int width, height; // Size of the arena
    private String studentNumber; // Your student number

    /**
     * Constructor to create a canvas of a specific size.
     * @param width Width of the canvas.
     * @param height Height of the canvas.
     * @param studentNumber Your student number.
     */
    public ConsoleCanvas(int width, int height, String studentNumber) {
        this.width = width + 2; // Add border
        this.height = height + 2; // Add border
        this.studentNumber = studentNumber;
        canvas = new char[this.height][this.width]; // Initialize the array

        initializeCanvas(); // Set up borders and student number
    }

    /**
     * Method to initialize the canvas with borders and the student number.
     */
    private void initializeCanvas() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (i == 0 || i == this.height - 1 || j == 0 || j == this.width - 1) {
                    canvas[i][j] = '#'; // Border
                } else {
                    canvas[i][j] = ' '; // Inside the arena
                }
            }
        }

        // Add student number to the top border
        int start = Math.max(1, (this.width - studentNumber.length()) / 2);
        for (int i = 0; i < studentNumber.length() && start + i < this.width - 1; i++) {
            canvas[0][start + i] = studentNumber.charAt(i);
        }
    }

    /**
     * Method to clear the canvas, keeping the borders intact.
     */
    public void clearCanvas() {
        for (int i = 1; i < this.height - 1; i++) {
            for (int j = 1; j < this.width - 1; j++) {
                canvas[i][j] = ' ';
            }
        }
    }

    /**
     * Method to place a robot on the canvas.
     * @param x X-coordinate of the robot.
     * @param y Y-coordinate of the robot.
     * @param symbol Character representing the robot.
     */
    public void showIt(int x, int y, char symbol) {
        if (x >= 0 && x < width - 2 && y >= 0 && y < height - 2) {
            canvas[y + 1][x + 1] = symbol; // Adjust for border
        } else {
            System.out.println("Error: Coordinates out of bounds.");
        }
    }

    /**
     * Method to convert the canvas to a string.
     * @return String representation of the canvas.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(canvas[i][j]);
            }
            sb.append('\n'); // New line for each row
        }
        return sb.toString();
    }
}
