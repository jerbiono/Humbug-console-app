package g54685.humbug.model;

/**
 * Gathers the elements necessary for the game to present a facade to the view
 *
 * @author Nader < 54685@etu.he2b.be>
 */
public class Game implements Model {

    private Board board;
    private Animal[] animals;
    private int currentLevel;
    private int remainingMoves;

    /**
     * Allows to return the board.
     *
     * @return the board.
     */
    @Override
    public Board getBoard() {
        return board;
    }

    /**
     * Allows to return the array of animals
     *
     * @return the array of animals.
     */
    @Override
    public Animal[] getAnimals() {
        return animals;
    }

    /**
     * Allows the start of levels in the game.
     *
     * @param level the current level of the game.
     */
    @Override
    public void startLevel(int level) {
        this.board = Level.getLevel(level).getBoard();
        this.animals = Level.getLevel(level).getAnimals();
        this.currentLevel = level;
        this.remainingMoves = Level.getLevel(level).getnMoves();
    }

    /**
     * Get the Level status of the the level on the game. , the level status can
     * be not started , in progress win or fail.
     *
     * @return the level status.
     */
    @Override
    public LevelStatus getLevelStatus() {
        if (this.remainingMoves == Level.getLevel(currentLevel).getnMoves()) {
            return LevelStatus.NOT_STARTED;
        } else {
            if (this.remainingMoves == 0) {
                for (Animal animal : this.animals) {
                    if (!animal.isOnStar()) {
                        return LevelStatus.FAIL;
                    }
                }
                return LevelStatus.WIN;
            }
            return LevelStatus.IN_PROGRESS;
        }
    }

    /**
     * Allows movement of the animal if there are no prohibitions.
     *
     * @param position the position of the animal.
     * @param direction the direction which the animal will take to move.
     */
    @Override
    public void move(Position position, Direction direction) {
        if (this.board == null) {
            throw new IllegalArgumentException("board not founded !");
        }
        if (this.animals == null) {
            throw new IllegalArgumentException("no animal is founded on the board !");
        }
        if (position == null) {
            throw new IllegalArgumentException("position not founded !");
        }
        if (direction == null) {
            throw new IllegalArgumentException("direction not founded !");
        }
        if (getLevelStatus().equals(LevelStatus.IN_PROGRESS) || getLevelStatus().equals(LevelStatus.NOT_STARTED)) {
            if (this.remainingMoves != 0) {
                for (Animal animal : animals) {
                    if (position.equals(animal.getPositionOnBoard()) && !animal.isOnStar()) {
                        animal.setPositionOnBoard(animal.move(board, direction, animals));
                        this.remainingMoves--;
                    }
                }
            }
        } else {
            throw new IllegalStateException("the level is finished you can't moves !");
        }
    }

    /**
     * Simple getter of Remaining Moves.
     *
     * @return the number of remaining moves.
     */
    @Override
    public int getRemainingMoves() {
        return this.remainingMoves;
    }
    
    
    /**
     * Simple getter of the current level.
     *
     * @return the current level.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

//    public Game(Board board, Animal[] animals) {
//        this.board = board;
//        this.animals = animals;
//    }
//    public static void main(String[] args) {
//        Board board = new Board(new Square[][]{
//            {new Square(GRASS), new Square(STAR), new Square(GRASS)},
//            {null, new Square(GRASS), new Square(STAR)},
//            {null, null, new Square(GRASS)}
//        });
//        Animal[] animals = new Animal[]{new Snail(new Position(0, 2) ), new Spider(new Position(0,0))};
//        Game game = new Game(board, animals);
//        try {
//        game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//        game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//        game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//        game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//                game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//        game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//        game.move(animals[0].getPositionOnBoard(), Direction.SOUTH);
//        game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//
//        game.move(animals[1].getPositionOnBoard(), Direction.EAST);
//                game.move(animals[1].getPositionOnBoard(), Direction.EAST);
//        game.move(animals[1].getPositionOnBoard(), Direction.EAST);
//
//                game.move(animals[1].getPositionOnBoard(), Direction.EAST);
//        game.move(animals[1].getPositionOnBoard(), Direction.EAST);
//
//        game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//        game.move(animals[0].getPositionOnBoard(), Direction.SOUTH);
//        game.move(animals[1].getPositionOnBoard(), Direction.SOUTH);
//        game.move(animals[1].getPositionOnBoard(), Direction.SOUTH);
//        System.out.println("snail  a le position" + animals[0].getPositionOnBoard());
//         game.move(animals[0].getPositionOnBoard(), Direction.SOUTH);
// game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//  game.move(animals[0].getPositionOnBoard(), Direction.SOUTH);
//   game.move(animals[0].getPositionOnBoard(), Direction.EAST);
//        } catch (IllegalArgumentException e) {
//            System.out.println("y a pas de board");
//        }
//    }

    
}
