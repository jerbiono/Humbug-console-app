
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
public class LadybirdTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS),new Square(GRASS)},
            {null, null, new Square(STAR),new Square(GRASS)}
        });
        animals = new Animal[] {
            new Ladybird(new Position(0, 0))
        };
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove() {
        System.out.println("move_general");
        Animal instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Animal[] animals = new Animal[]{
            new Ladybird(new Position(0,0)), new Ladybird(new Position(0,1))
            };
        Position expResult = new Position(0, 0); //don't move
        Position result = animals[0].move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_nextSecondSquare_notfree() {
        System.out.println("move next case not free");
       Animal[] animals = new Animal[]{
            new Ladybird(new Position(0,0)), new Ladybird(new Position(0,2))
            };
        Position expResult = new Position(0, 1); //don't move
        Position result = animals[0].move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

  
    
    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_next_onstar() {
        System.out.println("move next on star");
        animals[0].setPositionOnBoard(new Position(0, 2));
        Position expResult = new Position(2, 2);
        Position result = animals[0].move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_next_notinside_2() {
        System.out.println("move next case null");
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMove_next_caseNull() {
        System.out.println("move next case null");
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

       
    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveEastWall() {
        System.out.println("move case with wall");
        Animal instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 1); //.next(Direction.EAST);
        this.board.getSquares()[0][1].setEastWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveWestWall() {
        System.out.println("move case with wall");
        animals[0].setPositionOnBoard(new Position(0, 2));
        Position expResult = new Position(0, 1); //.next(Direction.WEST);
        this.board.getSquares()[0][1].setWestWall(true);
        Position result = animals[0].move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveNorthtWall() {
        System.out.println("move case with wall");
        animals[0].setPositionOnBoard(new Position(2, 3));
        Position expResult = new Position(1, 3); //.next(Direction.NORTH);
        this.board.getSquares()[1][3].setNorthWall(true);
        Position result = animals[0].move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveSouthWall() {
        System.out.println("move case with wall");
        animals[0].setPositionOnBoard(new Position(0, 3));
        Position expResult = new Position(2, 3); //.next(Direction.SOUTH);
        this.board.getSquares()[2][3].setSouthWall(true);
        Position result = animals[0].move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveOppositeEastWall() {
        System.out.println("move to opposite east wall");
        animals[0].setPositionOnBoard(new Position(0, 2));
        Position expResult = new Position(0, 2); 
        this.board.getSquares()[0][1].setEastWall(true);
        Position result = animals[0].move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveOppositeWestWall() {
        System.out.println("move to opposite west wall");
        Animal instance = (Ladybird) animals[0];
        Position expResult = new Position(0, 0); 
        this.board.getSquares()[0][1].setWestWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveOppositeNorthWall() {
        System.out.println("move to opposite North wall");
        animals[0].setPositionOnBoard(new Position(0, 3));
        Position expResult = new Position(1, 3); 
        this.board.getSquares()[2][3].setNorthWall(true);
        Position result = animals[0].move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of move method, of class Ladybird.
     */
    @Test
    public void testMoveOppositeSouthWall() {
        System.out.println("move to opposite North wall");
        animals[0].setPositionOnBoard(new Position(2, 3));
        Position expResult = new Position(2, 3); 
        this.board.getSquares()[1][3].setSouthWall(true);
        Position result = animals[0].move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
    }    
}
