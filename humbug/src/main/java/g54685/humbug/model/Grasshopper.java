
package g54685.humbug.model;

/**
 * the definition of the Grasshopper class that inherits the animal class. This class
 * allows the Grasshopper to move thanks to the move method.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Grasshopper extends Animal {
public Grasshopper(){
    
}
    /**
     * Constructor of Grasshopper.
     *
     * @param positionOnBoard the position of the Grasshopper on the board.
     */
    public Grasshopper(Position positionOnBoard) {
        super(positionOnBoard);
    }
    
 /**
     * Moves the animal and changes his position. If the animal falls, its
     * position is set to null. When the animal is on a star it disappears and
     * no longer blocks anyone. The square type star at this position should
     * become grass.The Grasshopper jumps to the next empty case.
     *
     * @param board the board where the Grasshopper moves on differnts
     * cases(squares).
     * @param direction the direction of the Grasshopper as he moves.
     * @param animals the type of animal who moves on the board
     * (spider,Snail,..)
     * @return returns the new position of the Grasshopper.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position movePosition = super.getPositionOnBoard().next(direction);
        if (!board.isInside(movePosition)) {
            return null;
        }

        for (Animal animal : animals) {
            while (movePosition.equals(animal.getPositionOnBoard())) {
                movePosition = new Position(movePosition.getRow() + direction.getDeltaRow(), movePosition.getColumn() + direction.getDeltaColumn());
            }
        }
        if (board.getSquareType(movePosition) == SquareType.STAR) {
            setOnStar(true);
            board.getSquares()[movePosition.getRow()][movePosition.getColumn()].setType(SquareType.GRASS);

        }
        return movePosition;
    }
    
    /**
     * Allows to return the name of the animal , the return will be a string.
     *
     * @return the name of the animal.
     */
    @Override
    public String toString() {
        return "GRASSHOPPER";
    }
}
