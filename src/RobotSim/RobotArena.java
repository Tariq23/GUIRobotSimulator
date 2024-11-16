package RobotSim;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class representing the arena where robots move.
 * The arena has a specific size and can contain multiple robots.
 */
public class RobotArena implements Serializable {
    private static final long serialVersionUID = 1L;
    private int width;
    private int height;
    private ArrayList<Robot> robots;

    /**
     * Constructor to create an arena of a specific size.
     * @param width - The width of the arena.
     * @param height - The height of the arena.
     */
    public RobotArena(int width, int height) {
        this.width = width;
        this.height = height;
        this.robots = new ArrayList<>();
    }

    /**
     * Gets the width of the arena.
     * @return - The width of the arena.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the arena.
     * @return - The height of the arena.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Adds a robot to a random position in the arena.
     */
    public void addRobot() {
        int x, y;
        do {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
        } while (getRobotAt(x, y) != null);
        robots.add(new Robot(x, y, Direction.getRandomDirection()));
    }

    /**
     * Returns a string representation of the arena and its robots.
     * @return - A string describing the arena and the positions of all robots.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Arena ").append(width).append(" by ").append(height).append("\n");
        for (Robot r : robots) {
            sb.append(r.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Checks if a robot can move to a specific position.
     * @param x - The x-coordinate to check.
     * @param y - The y-coordinate to check.
     * @return - True if the position is within the arena and not occupied by another robot, false otherwise.
     */
    public boolean canMoveHere(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return getRobotAt(x, y) == null;
    }

    /**
     * Finds a robot at a specific position.
     * @param x - The x-coordinate to check.
     * @param y - The y-coordinate to check.
     * @return - The robot at the specified position, or null if no robot is found.
     */
    public Robot getRobotAt(int x, int y) {
        for (Robot r : robots) {
            if (r.isHere(x, y)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Displays all robots on the provided canvas.
     * @param c - The canvas used to display the robots.
     */
    public void showRobots(ConsoleCanvas c) {
        for (Robot r : robots) {
            r.displayRobot(c);
        }
    }

    /**
     * Moves all robots in the arena.
     */
    public void moveAllRobots() {
        for (Robot r : robots) {
            r.tryToMove(this);
        }
    }
}
