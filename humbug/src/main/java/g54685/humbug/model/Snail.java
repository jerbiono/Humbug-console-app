package g54685.humbug.model;

/**
 * the definition of the snaill class that inherits the animal class. This class
 * allows the snail to move thanks to the move method.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Snail extends Animal {
    public Snail(){
        
    }
    /**
     * Constructor of Snail.
     *
     * @param positionOnBoard the position of the Snail on the board.
     */
    public Snail(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Moves the animal and changes his position. If the animal falls, its
     * position is set to null and the method also returns null. When the animal
     * is on a star it disappears and no longer blocks anyone. his onStar
     * attribute indicates that it has arrived. The square type star at this
     * position should become grass.The snail deplaces case by case he is too
     * slow.
     *
     * @param board the board where the Snail moves on differnts cases(squares).
     * @param direction the direction of the Snail as he moves.
     * @param animals the type of animal who moves on the board (spider,..) and
     * who can block the snail when he move..
     * @return returns the new position of the snail.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
       
        
        Position movePosition = super.getPositionOnBoard().next(direction);
        if (!board.isInside(movePosition)) {
            return null;
        }
        if (board.getSquares()[super.getPositionOnBoard().getRow()][super.getPositionOnBoard().getColumn()].hasWall(direction) || board.getSquares()[movePosition.getRow()][movePosition.getColumn()].hasWall(direction.opposite())) {
            return super.getPositionOnBoard();
        }
        for (Animal animal : animals) {
            if (movePosition.equals(animal.getPositionOnBoard())) {
                movePosition = super.getPositionOnBoard();
            }
        }
        if (board.getSquareType(movePosition) == SquareType.STAR) {
            super.setOnStar(true);
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
        return "SNAIL";
    }

}
