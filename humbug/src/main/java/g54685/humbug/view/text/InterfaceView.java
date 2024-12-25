package g54685.humbug.view.text;

import g54685.humbug.model.Animal;
import g54685.humbug.model.Board;
import g54685.humbug.model.Direction;
import g54685.humbug.model.Position;

/**
 * InterfaceView class that has the following methods.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public interface InterfaceView {

    /**
     * Asking the player for a new position.
     *
     * @return a new position given by the player.
     */
    public Position askPosition();

    /**
     * Asking player for a new direction.
     *
     * @return the new Direction given by the player.
     */
    public Direction askDirection();

    /**
     * Display an error message.
     *
     * @param message the given message of error.
     */
    public void displayError(String message);

    /**
     * display the board and animals on the board of the game.
     *
     * @param board the given board to be displayed.
     * @param animals the given array of animals to be displayed.
     */
    public void displayBoard(Board board, Animal... animals);

    /**
     * Print the number of remaining moves.
     *
     * @param remainingMoves the given remaining moves.
     */
    public void displayRemainingMoves(int remainingMoves);
}
