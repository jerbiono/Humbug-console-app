package g54685.humbug.model;

/**
 * the definition of the spider class that inherits the animal class. This class
 * allows the spider to move thanks to the move method.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Spider extends Animal {

    public Spider() {

    }

    /**
     * Constructor of Spider.
     *
     * @param positionOnBoard the position of the Spider on the board.
     */
    public Spider(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Moves the animal and changes his position. If the animal falls, its
     * position is set to null and the method also returns null. When the animal
     * is on a star it disappears and no longer blocks anyone. his onStar
     * attribute indicates that it has arrived. The square type star at this
     * position should become grass.THe spider stops only if he was blocked by
     * another animal.
     *
     * @param board the board where the Spider moves on differnts
     * cases(squares).
     * @param direction the direction of the Spider as he moves.
     * @param animals the type of animal who moves on the board (snail,..) and
     * who can block the spider when he move..
     * @return returns the new position of the spider.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position movePosition = super.getPositionOnBoard();
        while (board.isInside(movePosition.next(direction)) && super.isEmpty(movePosition.next(direction), animals)
                && !(board.getSquares()[movePosition.getRow()][movePosition.getColumn()].hasWall(direction)
                || board.getSquares()[movePosition.next(direction).getRow()][movePosition.next(direction).getColumn()].hasWall(direction.opposite()))) {
            movePosition = movePosition.next(direction);
        }
        if (!board.isInside(movePosition.next(direction)) && !board.getSquares()[movePosition.getRow()][movePosition.getColumn()].hasWall(direction)) {
            return null;
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
        return "SPIDER";
    }

}
