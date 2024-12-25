
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
public class SnailTest {
    
   
    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[] {
            new Snail(new Position(0, 0)),
            new Snail(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Animal instance = (Snail) animals[0];
        Position expResult = new Position(0, 1); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Snail instance = (Snail) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 0); //don't move
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

  
    
    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_onstar() {
        System.out.println("move next on star");
        Snail instance = (Snail) animals[1];
        Position expResult = new Position(2, 2);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notinside_2() {
        System.out.println("move next case null");
        Snail instance = (Snail) animals[0];
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

       
    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMoveEastWall() {
        System.out.println("move case with wall");
        Animal instance = (Snail) animals[0];
        Position expResult = new Position(0, 0); //.next(Direction.EAST);
        this.board.getSquares()[0][0].setEastWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMoveWestWall() {
        System.out.println("move case with wall");
        Animal instance = (Snail) animals[1];
        Position expResult = new Position(1, 2); //.next(Direction.WEST);
        this.board.getSquares()[1][2].setWestWall(true);
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMoveNorthtWall() {
        System.out.println("move case with wall");
        Animal instance = (Snail) animals[1];
        animals[1].setPositionOnBoard(new Position(1, 1));
        Position expResult = new Position(1, 1); //.next(Direction.NORTH);
        this.board.getSquares()[1][1].setNorthWall(true);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMoveSouthWall() {
        System.out.println("move case with wall");
        Animal instance = (Snail) animals[0];
        animals[0].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 1); //.next(Direction.SOUTH);
        this.board.getSquares()[0][1].setSouthWall(true);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMoveOppositeEastWall() {
        System.out.println("move to opposite east wall");
        Animal instance = (Snail) animals[0];
        animals[0].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 1); 
        this.board.getSquares()[0][0].setEastWall(true);
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMoveOppositeWestWall() {
        System.out.println("move to opposite west wall");
        Animal instance = (Snail) animals[0];
        animals[0].setPositionOnBoard(new Position(0, 0));
        Position expResult = new Position(0, 0); 
        this.board.getSquares()[0][1].setWestWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMoveOppositeNorthWall() {
        System.out.println("move to opposite North wall");
        Animal instance = (Snail) animals[0];
        animals[0].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 1); 
        this.board.getSquares()[1][1].setNorthWall(true);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMoveOppositeSouthWall() {
        System.out.println("move to opposite North wall");
        Animal instance = (Snail) animals[0];
        animals[0].setPositionOnBoard(new Position(1, 1));
        Position expResult = new Position(1, 1); 
        this.board.getSquares()[0][1].setSouthWall(true);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }

    
}
