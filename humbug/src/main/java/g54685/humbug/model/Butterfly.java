
package g54685.humbug.model;

/**
 * the definition of the Butterfly class that inherits the animal class. This
 * class allows the Butterfly to move thanks to the move method.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Butterfly extends Animal {
    
public Butterfly(){
    
}

    /**
     * Constructor of Butterfly.
     *
     * @param positionOnBoard the position of the Butterfly on the board.
     */
    public Butterfly(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Moves the animal and changes his position. If the animal falls, its
     * position is set to null. When the animal is on a star it disappears and
     * no longer blocks anyone. The square type star at this position should
     * become grass. The Butterfly fly three squares and if the last square is
     * occupied by an animal he stops at the next square.
     *
     * @param board the board where the Butterfly moves on differnts
     * cases(squares).
     * @param direction the direction of the Butterfly as he moves.
     * @param animals the type of animal who moves on the board
     * (spider,Snail,..)
     * @return returns the new position of the Butterfly.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position movePosition = super.getPositionOnBoard().next(direction).next(direction).next(direction);
        while (existAnimal(movePosition, animals)) {
            movePosition = new Position(movePosition.getRow() + direction.getDeltaRow(), movePosition.getColumn() + direction.getDeltaColumn());
        }
        if (!board.isInside(movePosition)) {
            return null;
        }
        if (board.getSquareType(movePosition) == SquareType.STAR) {
            setOnStar(true);
            board.getSquares()[movePosition.getRow()][movePosition.getColumn()].setType(SquareType.GRASS);

        }

        return movePosition;

    }

    /**
     * Checks if there is an animal in the same position as the current animal
     * when moving.
     *
     * @param movePosition the position of the currecnt animal.
     * @param animals the animals who are on the board.
     * @return true if there is an other animal on the same position of the
     * current animal , false otherwise.
     */
    public boolean existAnimal(Position movePosition, Animal... animals) {
        for (Animal animal : animals) {
            if (movePosition.equals(animal.getPositionOnBoard())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Allows to return the name of the animal , the return will be a string.
     *
     * @return the name of the animal.
     */
    @Override
    public String toString() {
        return "BUTTERFLY";
    }
}


