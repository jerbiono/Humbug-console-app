
package g54685.humbug.model;

import static g54685.humbug.model.SquareType.GRASS;
import static g54685.humbug.model.SquareType.STAR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Nader
 */
public class BumbelbeeTest {
    
    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS),new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS),new Square(GRASS)},
            {null, new Square(STAR), new Square(GRASS), new Square(GRASS),new Square(GRASS)}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Bumbelbee(new Position(1, 1))
        };
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Animal instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Bumbelbee instance = (Bumbelbee) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 2));
        Position expResult = new Position(0, 3); //move to the next empty case
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_next_onstar() {
        System.out.println("move next on star");
        animals[0].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(2, 1);
        Position result = animals[0].move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_next_notinside_2() {
        System.out.println("move next case null");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_next_case_null() {
        System.out.println("move next case null");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null; // move and fall, Position(2,0)
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Bumbelbee.
     */
    @Test
    public void testMove_nextTwoSquareNotEmpty() {
        System.out.println("move next case empty");
        Animal[] animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Bumbelbee(new Position(0, 2)),
            new Bumbelbee(new Position(0, 3))
        };
        Position expResult = new Position(0,4); 
        Position result = animals[0].move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    
}
