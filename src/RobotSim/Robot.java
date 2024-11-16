package RobotSim;

import java.io.Serializable;

/**
 * Class representing a Robot in the arena.
 * Each Robot has a unique ID, position (x, y), and direction.
 */
public class Robot implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x, y; // Robot's position
    private static int idCounter = 0; // Counter for unique IDs
    private int id; // Robot's unique ID
    private Direction direction; // Direction the robot will move

    /**
     * Constructor to create a robot at a specific position with a direction.
     * @param x X-coordinate of the robot.
     * @param y Y-coordinate of the robot.
     * @param direction Initial direction of the robot.
     */
    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.id = idCounter++; // Assign unique ID and increment counter
    }

    /**
     * Method to return robot's details as a string.
     * @return String representation of the robot.
     */
    public String toString() {
        return "Robot " + id + " is at " + x + ", " + y + " facing " + direction;
    }

    /**
     * Method to display the robot on the canvas.
     * @param c The canvas used to display the robot.
     */
    public void displayRobot(ConsoleCanvas c) {
        c.showIt(x, y, 'R'); // Place 'R' at the robot's position
    }

    /**
     * Method to check if the robot is at a specific position.
     * @param sx X-coordinate to check.
     * @param sy Y-coordinate to check.
     * @return True if the robot is at (sx, sy), false otherwise.
     */
    public boolean isHere(int sx, int sy) {
        return this.x == sx && this.y == sy; // Return true if robot is at (sx, sy)
    }

    /**
     * Method to try to move the robot in its current direction.
     * @param arena The arena in which the robot is moving.
     */
    public void tryToMove(RobotArena arena) {
        int newX = x, newY = y;
        switch (direction) {
            case NORTH: newY--; if (newY < 0) { newY = arena.getHeight() - 1; } break;
            case EAST: newX++; if (newX >= arena.getWidth()) { newX = 0; } break;
            case SOUTH: newY++; if (newY >= arena.getHeight()) { newY = 0; } break;
            case WEST: newX--; if (newX < 0) { newX = arena.getWidth() - 1; } break;
        }

        if (arena.canMoveHere(newX, newY)) {
            x = newX;
            y = newY;
        } else {
            direction = direction.next(); // Change direction if move is not possible
        }
    }
}
