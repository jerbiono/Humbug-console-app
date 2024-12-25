package g54685.humbug.model;

/**
 * Square on the board. A square has a type grass or star and it’s all. A square
 * doesn’t know where it is on the board.
 *
 * @author Nader< 54685@etu.he2b.be >
 */
public class Square {

    private SquareType type;
    private boolean northWall;
    private boolean southWall;
    private boolean westWall;
    private boolean eastWall;
    public Square() {
        
    }

    /**
     * Constructor of Square on board.
     *
     * @param type Square is grass or star
     */
    public Square(SquareType type) {
        this.type = type;
        this.eastWall = false;
        this.northWall = false;
        this.westWall = false;
        this.southWall = false;
    }

    /**
     * Simple getter of type.
     *
     * @return type of Square
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Simple setter of type.
     *
     * @param type to set.
     */
    public void setType(SquareType type) {
        this.type = type;
    }

    /**
     * Simple setter of northWall.
     *
     * @param northWall to set.
     */
    public void setNorthWall(boolean northWall) {
        this.northWall = northWall;
    }

    /**
     * Simple setter of southWall.
     *
     * @param southWall to set.
     */
    public void setSouthWall(boolean southWall) {
        this.southWall = southWall;
    }

    /**
     * Simple setter of westWall.
     *
     * @param westWall to set.
     */
    public void setWestWall(boolean westWall) {
        this.westWall = westWall;
    }

    /**
     * Simple setter of eastWall.
     *
     * @param eastWall to set.
     */
    public void setEastWall(boolean eastWall) {
        this.eastWall = eastWall;
    }

    /**
     * Checks in the chosen direction if there is a wall.
     *
     * @param direction the direction to move.
     * @return true if ther is a wall , otherwise return false.
     */
    public boolean hasWall(Direction direction) {
        switch (direction) {
            case EAST:
                return this.eastWall;
            case WEST:
                return this.westWall;
            case SOUTH:
                return southWall;
            default:
                return northWall;
        }
    }
}
