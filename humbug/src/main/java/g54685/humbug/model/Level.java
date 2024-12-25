package g54685.humbug.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * The composition of any level on the game. It contains the animals , the board
 * and the number of moves can be used by the user.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Level {

    /**
     * Get the choiced level from the first levels the last level of the game.
     *
     * @param level the given level.
     * @return the level choiced with it's board animal and the remainging
     * moves.
     */
    public static Level getLevel(int level) {
       return readLevel(level);
    }
    
    /**
     * Allows to return the given level.
     * @param n the given level.
     * @return the level.
     */
    private static Level readLevel(int n) {
        try {
            var objectMapper = new ObjectMapper();
            var inputStream = Level.class.getResourceAsStream(
                    "/data/level-" + n + ".json");
            var level = objectMapper.readValue(inputStream, Level.class);
            return level;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private Board board;
    private Animal[] animals;
    private int nMoves;


    /**
     * Default constructor of level.
     */
    public Level() {

    }

    /**
     * Constructor of Level.
     *
     * @param board the board of the game.
     * @param animals the animals on the board.
     * @param nMoves the number of moves which are authorized.
     */
    public Level(Board board, int nMoves, Animal... animals) {
        this.board = board;
        this.animals = animals;
        this.nMoves = nMoves;
    }

    /**
     * Simple getter of board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Simple getter animals.
     *
     * @return the array of animals
     */
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Simple getter of moves.
     *
     * @return the number of moves.
     */
    public int getnMoves() {
        return nMoves;
    }

}
