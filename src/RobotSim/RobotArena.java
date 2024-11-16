package RobotSim;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing the arena where robots move.
 * The arena has a specific size and can contain multiple robots.
 */
public class RobotArena implements Serializable {
    private static final long serialVersionUID = 1L;
    private int width, height; // Size of the arena
    private ArrayList<Robot> robots; // List to store robots

    /**
     * Constructor to create an arena of a specific size.
     * @param width Width of the arena.
     * @param height Height of the arena.
     */
    public RobotArena(int width, int height) {
        this.width = width;
        this.height = height;
        this.robots = new ArrayList<>(); // Initialize the list of robots
    }

    /**
     * Method to add a robot at a random position in the arena.
     */
    public void addRobot() {
        int x, y;
        do {
            x = (int) (Math.random() * width); // Random x position
            y = (int) (Math.random() * height); // Random y position
        } while (getRobotAt(x, y) != null); // Repeat until a free position is found
        robots.add(new Robot(x, y, Direction.getRandomDirection())); // Add new robot to the list
    }

    /**
     * Method to return arena details as a string.
     * @return String representation of the arena and its robots.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Arena ").append(width).append(" by ").append(height).append("\n");
        for (Robot r : robots) {
            sb.append(r.toString()).append("\n"); // Add each robot's details
        }
        return sb.toString();
    }

    /**
     * Method to check if a robot can move to a specific position.
     * @param x X-coordinate to check.
     * @param y Y-coordinate to check.
     * @return True if the position is within the arena and free, false otherwise.
     */
    public boolean canMoveHere(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false; // Position is outside the arena
        }
        return getRobotAt(x, y) == null; // Check if position is free
    }

    /**
     * Method to find a robot at a specific position.
     * @param x X-coordinate to check.
     * @param y Y-coordinate to check.
     * @return The robot at (x, y) or null if no robot is found.
     */
    public Robot getRobotAt(int x, int y) {
        for (Robot r : robots) {
            if (r.isHere(x, y)) {
                return r; // Return the robot if found at (x, y)
            }
        }
        return null; // Return null if no robot is found at (x, y)
    }

    /**
     * Method to show all robots on the canvas.
     * @param c The canvas used to display the robots.
     */
    public void showRobots(ConsoleCanvas c) {
        c.clearCanvas(); // Clear the canvas before showing robots
        for (Robot r : robots) {
            r.displayRobot(c); // Display each robot
        }
    }

    /**
     * Method to move all robots in the arena.
     */
    public void moveAllRobots() {
        for (Robot r : robots) {
            r.tryToMove(this); // Try to move each robot
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
