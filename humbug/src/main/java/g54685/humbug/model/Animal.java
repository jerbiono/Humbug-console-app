package g54685.humbug.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use = Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @Type(value = Bumbelbee.class),
    @Type(value = Grasshopper.class),
    @Type(value = Ladybird.class),
    @Type(value = Snail.class),
    @Type(value = Spider.class),})
/**
 * the animal's moves on the board. the animal know where he is on the board.
 * the animal have an initial position on the board , he arrived when we he stop
 * exaclty on a star square.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public abstract class Animal {

    private Position positionOnBoard;
    private boolean onStar;

    /**
     * Default constructor of Animal.
     */
    public Animal() {

    }

    /**
     * Constructor of Animal.
     *
     * @param positionOnBoard the position of the animal on the board.
     */
    public Animal(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
        this.onStar = false;

    }

    /**
     * Simple getter of positionOnBoard.
     *
     * @return the positionOnBoard.
     */
    public Position getPositionOnBoard() {
        return positionOnBoard;
    }

    /**
     * Simple getter of onStar.
     *
     * @return the value of onStar.
     */
    public boolean isOnStar() {
        return onStar;
    }

    /**
     * Simple setter of positionOnBoard.Update a new value of positionOnBoard.
     *
     * @param positionOnBoard to set.
     */
    public void setPositionOnBoard(Position positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    /**
     * Simple setter of onStar.Update a new value of onStar.
     *
     * @param onStar to set.
     */
    public void setOnStar(boolean onStar) {
        this.onStar = onStar;
    }

    /**
     * Moves the animal and changes his position. If the animal falls, its
     * position is set to null and the method also returns null. When the animal
     * is on a star it disappears and no longer blocks anyone. We will leave it
     * in the list of animals and his onStar attribute indicates that it has
     * arrived. The square type star at this position should become grass.
     *
     * @param board the board where the animals moves on differnts
     * cases(squares).
     * @param direction the direction of the animal as he moves.
     * @param animals the type of animal who moves on the board
     * (snail,spider..).
     * @return returns the new position of the animal.
     */
    public abstract Position move(Board board, Direction direction, Animal... animals);
    
    /**
     * Checks if there is an other animal with the same position given.
     *
     * @param position the position of the animal.
     * @param animals the animals on the board.
     * @return true if there are an animal on the same square , otherwise return
     * false.
     */
    public boolean isEmpty(Position position, Animal... animals) {

        for (Animal animal : animals) {
            if (animal.getPositionOnBoard().equals(position) && !animal.isOnStar()) {
                return false;
            }
        }
        return true;
    }
}
