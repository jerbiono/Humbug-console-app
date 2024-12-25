package g54685.humbug.model;

/**
 * Direction of the animal when he move on the board. A direction can be
 * indicated to the NORTH or to the SOUTH , or to the EAST or to the WEST. A
 * direction can be changed according the change of deltaRow and deltaColumn.
 *
 * @author Nader < 54685@etu.he2b.Be >
 */
public enum Direction {
    NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);
    private int deltaRow;
    private int deltaColumn;

    /**
     * Get the value of deltaRow.
     *
     * @return the value of deltaRow.
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Constructor of direction on the board.
     *
     * @param deltaRow indicates the variation of the vertical direction.
     * @param deltaColumn indicates the variation of the horizontale direction.
     */
    private Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * Get the value of deltaColumn
     *
     * @return the value of deltaColumn
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }

    /**
     * Reverse the direction.
     *
     * @return the opposite direction.
     */
    public Direction opposite() {
        switch (this) {
            case SOUTH:
                return Direction.NORTH;
            case EAST:
                return Direction.WEST;
            case NORTH:
                return Direction.SOUTH;
            default:
                return Direction.EAST;
        }
    }
}
