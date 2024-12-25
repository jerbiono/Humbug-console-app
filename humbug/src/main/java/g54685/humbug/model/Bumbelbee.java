package g54685.humbug.model;

/**
 * the definition of the Grasshopper class that inherits the animal class. This
 * class allows the Grasshopper to move thanks to the move method.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Bumbelbee extends Animal {
    public Bumbelbee(){
        
    }
    /**
     * Constructor of Bumbelbee.
     *
     * @param positionOnBoard the position of the Bumbelbee on the board.
     */
    public Bumbelbee(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Moves the animal and changes his position. If the animal falls, its
     * position is set to null. When the animal is on a star it disappears and
     * no longer blocks anyone. The square type star at this position should
     * become grass. The Bumbelbee fly two squares and if the last square is
     * occupied by an animal he stops at the next square.
     *
     * @param board the board where the Bumbelbee moves on differnts
     * cases(squares).
     * @param direction the direction of the Bumbelbee as he moves.
     * @param animals the type of animal who moves on the board
     * (spider,Snail,..)
     * @return returns the new position of the Bumbelbee.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position movePosition = super.getPositionOnBoard().next(direction).next(direction);
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
        return "BUMBELBEE";
    }
}
