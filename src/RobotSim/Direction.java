package RobotSim;

/**
 * Enum representing the four possible directions a robot can move.
 */
public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     * Method to get a random direction.
     * @return - A random direction.
     */
    public static Direction getRandomDirection() {
        return values()[(int) (Math.random() * values().length)];
    }

    /**
     * Method to get the next direction in NESW order.
     * @return - The next direction.
     */
    public Direction next() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    /**
     * Method to get the opposite direction.
     * @return - The opposite direction.
     */
    public Direction opposite() {
        switch (this) {
            case NORTH: return SOUTH;
            case EAST: return WEST;
            case SOUTH: return NORTH;
            case WEST: return EAST;
            default: throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
