package RobotSim;

import java.io.Serializable;

/**
 * Class representing a Robot in the arena.
 * Each Robot has a unique ID, position (x, y), and direction.
 */
public class Robot implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    private static int idCounter = 0;
    private int id;
    private Direction direction;

    /**
     * Constructor to create a robot at a specific position with a direction.
     * @param x - The x-coordinate of the robot.
     * @param y - The y-coordinate of the robot.
     * @param - direction The initial direction of the robot.
     */
    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.id = idCounter++;
    }

    /**
     * Gets the x-coordinate of the robot.
     * @return The x-coordinate of the robot.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the robot.
     * @param x - The new x-coordinate of the robot.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the robot.
     * @return - The y-coordinate of the robot.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the robot.
     * @param y - The new y-coordinate of the robot.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the ID of the robot.
     * @return - The unique ID of the robot.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the direction of the robot.
     * @return - The current direction of the robot.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the robot.
     * @param - direction The new direction of the robot.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Returns a string representation of the robot.
     * @return - A string describing the robot's ID, position, and direction.
     */
    @Override
    public String toString() {
        return "Robot " + id + " is at (" + x + ", " + y + ") facing " + direction;
    }

    /**
     * Displays the robot on the canvas.
     * @param c - The canvas used to display the robot.
     */
    public void displayRobot(ConsoleCanvas c) {
        char symbol = switch (direction) {
            case NORTH -> '^';
            case EAST -> '>';
            case SOUTH -> 'v';
            case WEST -> '<';
        };
        c.showIt(x, y, symbol);
    }

    /**
     * Checks if the robot is at a specific position.
     * @param sx - The x-coordinate to check.
     * @param sy - The y-coordinate to check.
     * @return - True if the robot is at (sx, sy), false otherwise.
     */
    public boolean isHere(int sx, int sy) {
        return this.x == sx && this.y == sy;
    }

    /**
     * Attempts to move the robot in its current direction.
     * If the move is not possible, the robot changes direction.
     * @param arena - The arena in which the robot is moving.
     */
    public void tryToMove(RobotArena arena) {
        int newX = x, newY = y;
        switch (direction) {
            case NORTH: newY--; break;
            case EAST: newX++; break;
            case SOUTH: newY++; break;
            case WEST: newX--; break;
        }

        if (arena.canMoveHere(newX, newY)) {
            setX(newX);
            setY(newY);
        } else {
            setDirection(direction.next());
        }
    }
}
