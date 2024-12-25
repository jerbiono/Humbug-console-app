/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54685.humbug.model;

import static g54685.humbug.model.SquareType.GRASS;
import static g54685.humbug.model.SquareType.STAR;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Nader
 */
public class SpiderTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove() {
        System.out.println("move and fall");
        Spider instance = (Spider) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_endline() {
        System.out.println("move end line and fall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        Spider instance = (Spider) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_tootheranimal() {
        System.out.println("move to other animal");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals[1] = new Snail(new Position(0, 2));
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Spider instance = (Spider) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 0); //don't move
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notinside() {
        System.out.println("move next case null and fall");
        Spider instance = (Spider) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    @Test
    public void testMove_passOnStar() {
        System.out.println("move and pass on star without win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertFalse(animals[0].isOnStar());
        assertFalse(board.getSquareType(new Position(0, 1)) == GRASS);
    }

    @Test
    public void testMove_nextOnStar() {
        System.out.println("move, next on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(STAR), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

     /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_firstCaseWithEastWall() {
        System.out.println("first case with east wall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(0, 2))
        };
        Position expResult = new Position (0,0);
       board.getSquares()[0][0].setEastWall(true);
        Position result = animals[0].move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
       
    }
    
     /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_firstCaseWithWestWall() {
        System.out.println("first case with west wall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
        };
        Position expResult = new Position (0,0);
       board.getSquares()[0][0].setWestWall(true);
        Position result = animals[0].move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
       
    }
    
     /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_firstCaseWithNorthWall() {
        System.out.println("first case with north wall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            
        };
        Position expResult = new Position (0,0);
       board.getSquares()[0][0].setNorthWall(true);
        Position result = animals[0].move(board, Direction.NORTH, animals);
        assertEquals(expResult, result);
       
    }
    
     /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_firstCaseWithSOUTHWall() {
        System.out.println("first case with south wall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(2,0))
        };
        Position expResult = new Position (0,0);
       board.getSquares()[0][0].setSouthWall(true);
        Position result = animals[0].move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_oppositeDirectionWithEastWall() {
        System.out.println("oppositeDirectionWithEastWall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 2)),
            
        };
        Position expResult = new Position (0,2);
       board.getSquares()[0][1].setEastWall(true);
        Position result = animals[0].move(board, Direction.WEST, animals);     
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_oppositeDirectionWithWestWall() {
        System.out.println("oppositeDirectionWithEAstWall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            
        };
        Position expResult = new Position (0,1);
       board.getSquares()[0][2].setWestWall(true);
        Position result = animals[0].move(board, Direction.EAST, animals);     
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_oppositeDirectionWithNorthWall() {
        System.out.println("oppositeDirectionWithNorthtWall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(1, 0)),
            
        };
        Position expResult = new Position (1,0);
       board.getSquares()[2][0].setNorthWall(true);
        Position result = animals[0].move(board, Direction.SOUTH, animals);     
        assertEquals(expResult, result);
       
    }
    
     /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_oppositeDirectionWithSouthWall() {
        System.out.println("oppositeDirectionWithSouthWall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(2, 0)),
            
        };
        Position expResult = new Position (2,0);
       board.getSquares()[1][0].setSouthWall(true);
        Position result = animals[0].move(board, Direction.NORTH, animals);     
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_caseWithWallFromAllDirection() {
        System.out.println("caseWithWallFromAllDirection");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            
        };
        Position expResult = new Position (0,1);
       board.getSquares()[0][2].setSouthWall(true);
       board.getSquares()[0][2].setEastWall(true);
       board.getSquares()[0][2].setNorthWall(true);
       board.getSquares()[0][2].setWestWall(true);
        Position result = animals[0].move(board, Direction.EAST, animals);     
        assertEquals(expResult, result);
       
    }
    
    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_starWithWall() {
        System.out.println("squareWithWall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS),null, new Square(GRASS)},
            {new Square(GRASS), null, new Square(GRASS)},
            {new Square(STAR), new Square(GRASS), new Square(GRASS)}
        });
        animals = new Animal[]{
            new Spider(new Position(3, 2)),
            
        };
        Position expResult = new Position (3,0);
       board.getSquares()[3][0].setWestWall(true);
        Position result = animals[0].move(board, Direction.WEST, animals);     
        assertEquals(expResult, result);
       
    }
}
