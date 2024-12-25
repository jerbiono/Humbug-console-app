package g54685.humbug.model;

/**
 * **
 * Model class that has the following methods.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public interface Model {

    /**
     * Allows to return the board.
     *
     * @return the board.
     */
    public Board getBoard();

    /**
     * Allows to return the array of animals
     *
     * @return the array of animals.
     */
    public Animal[] getAnimals();

    /**
     * Allows the start and levels of the game.
     *
     * @param level the current level of the game.
     */
    public void startLevel(int level);

    /**
     * Allows movement of the animal if there are no prohibitions.
     *
     * @param position the position of the animal.
     * @param direction the direction which the animal will take to move.
     */
    public void move(Position position, Direction direction);

    /**
     * Get the Level status of the the level on the game. , the level status can
     * be not started , in progress win or fail.
     *
     * @return the level status.
     */
    public LevelStatus getLevelStatus();

    /**
     * Simple getter of Remaining Moves.
     *
     * @return the number of remaining moves.
     */
    public int getRemainingMoves();

}
