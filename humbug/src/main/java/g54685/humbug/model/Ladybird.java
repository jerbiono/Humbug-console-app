package g54685.humbug.model;

/**
 * the definition of the ladybird class that inherits the animal class. This
 * class allows the ladybird to move thanks to the move method.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Ladybird extends Animal {
    
    /**
    *
    * Constructor by default of Ladybird.
    */
        public Ladybird(){
    
    }
    
    /**
     * Constructor of Ladybird.
     *
     * @param positionOnBoard the position of the ladybird on the board.
     */
    public Ladybird(Position positionOnBoard) {
        super(positionOnBoard);
    }

    /**
     * Moves the animal and changes his position. If the animal falls, its
     * position is set to null and the method also returns null. When the animal
     * is on a star it disappears and no longer blocks anyone. The square type
     * star at this position should become grass. The ladybird moves two squares
     * and if it meets a wall or an animal it remains in its initial position
     * and it does not move.
     *
     * @param board the board where the ladybird moves on differnts
     * cases(squares).
     * @param direction the direction of the ladybird as he moves.
     * @param animals the type of animal who moves on the board (spider,snail..)
     * and who can block the ladybird when he move..
     * @return returns the new position of the ladybird.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position initialPosition, movePosition;
        initialPosition = movePosition = super.getPositionOnBoard();
        int move = 0;
        boolean exist = false;
        while (move < 2 && !exist) {
            movePosition = initialPosition.next(direction);
            if (!board.isInside(movePosition)) {
                return null;
            }
            if (board.getSquares()[initialPosition.getRow()][initialPosition.getColumn()].hasWall(direction) || board.getSquares()[movePosition.getRow()][movePosition.getColumn()].hasWall(direction.opposite())) {
                movePosition = initialPosition;
                exist = true;
            } else {
                if (!super.isEmpty(movePosition, animals)) {
                    movePosition = initialPosition;
                    exist = true;
                } else {
                    initialPosition = movePosition;
                    move++;
                }
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
        return "LADYBIRD";
    }

}
