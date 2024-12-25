package g54685.humbug.model;

/**
 * Create the board of the game. the initial board is composed of
 * two-dimensional array wihch contains square types and where we can verify any
 * position on the board.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Board {


    private Square[][] squares;
    
    /**
     * Default constructor of Board.
     */
    public Board() {
        
    }

    /**
     * Constructor of board.
     *
     * @param squares an array of square. boxes of the array can be
     * squares(grass/star) or nulls.
     *
     */
    protected Board(Square[][] squares) {
        this.squares = squares;
    }

    /**
     * Checks if the position is on the board.
     *
     * @param position the position to check.
     * @return true if position was a star or a grass , throw an exception
     * otherwise.
     */
    public boolean isInside(Position position) {

        if (position.getRow() < 0
                || position.getRow() >= squares.length
                || position.getColumn() < 0
                || position.getColumn() >= squares[0].length) {
            return false;
        }
        if (this.squares[position.getRow()][position.getColumn()] == null) {
            return false;

        }
        if (position == null) {
            throw new IllegalArgumentException("this position does not exist on the board !");
        }

        return true;
    }

    /**
     * Checks the square type of the given position on the board.
     *
     * @param position the position to check.
     * @return the type of square , it can be star or grass otherwise throw an
     * exception.
     */
    public SquareType getSquareType(Position position) {

        if (position.getRow() < 0
                || position.getRow() >= squares.length
                || position.getColumn() < 0
                || position.getColumn() >= squares[0].length) {
            throw new IllegalArgumentException("this position does not exist on the board !");
        }

        if (this.squares[position.getRow()][position.getColumn()] == null) {
            throw new IllegalArgumentException("this position does is not a square of the board !");
        }
        return (this.squares[position.getRow()][position.getColumn()].getType());
    }

    /**
     * Calculates the number of rows on the board.
     *
     * @return the total numbers of rows.
     */
    public int getNbRow() {

        return this.squares.length;
    }

    /**
     * Calculates the number of columns on the board.
     *
     * @return the total numbers of columns.
     */
    public int getNbColumn() {

        return this.squares[0].length;
    }

    /**
     * Get the array of squares.
     *
     * @return the array of squares.
     */
    public Square[][] getSquares() {
        return squares;
    }

}
