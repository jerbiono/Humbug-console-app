package g54685.humbug.view.text;

import java.util.Scanner;
import g54685.humbug.model.*;
import static g54685.humbug.model.SquareType.GRASS;
import static g54685.humbug.model.SquareType.STAR;

/**
 * Display the board of the game and an error message. Asking player for a new
 * position and a new direction.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class View implements InterfaceView {

    /**
     *
     * robust reading to get an integer.
     *
     * @return a number.
     */
    public static int readNumber() {
        Scanner sc = new Scanner(System.in);
        int number;
        while (!sc.hasNextInt()) {
            System.out.println("the input must be an integer , please tape a number!");
            sc.next();
        }
        number = sc.nextInt();

        return number;
    }

    /**
     * print the color of squares.
     *
     * @param board the given board.
     * @param Row the given row of the board.
     * @param col the given col of the board.
     * @param name the square's name.
     * @param color the background color of the square.
     */
    public static void printColor(Board board, int Row, int col, String name, TerminalColor color) {
        if (board.getSquares()[Row][col].hasWall(Direction.EAST) && board.getSquares()[Row][col].hasWall(Direction.WEST)) {
            System.out.print(TerminalColor.BG_BLUE.toString() + "|" + "\033[m" + color.toString() + TerminalColor.BLUE.toString() + "   " + name + "    " + "\033[m" + TerminalColor.BG_BLUE.toString() + "|" + "\033[m");
        } else if (board.getSquares()[Row][col].hasWall(Direction.EAST)) {
            System.out.print(color.toString() + TerminalColor.BLUE.toString() + "[   " + name + "    " + "\033[m" + TerminalColor.BG_BLUE.toString() + "|" + "\033[m");
        } else if (board.getSquares()[Row][col].hasWall(Direction.WEST)) {
            System.out.print(TerminalColor.BG_BLUE.toString() + "|" + "\033[m" + color.toString() + TerminalColor.BLUE.toString() + "    " + name + "   ]" + "\033[m");
        } else {
            System.out.print(color.toString() + TerminalColor.BLUE.toString() + "[    " + name + "   ]" + "\033[m");
        }
    }

    /**
     * Display an error message.
     *
     * @param message the given message of error.
     */
    @Override
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * Asking the player for a new position.
     *
     * @return a new position given by the player.
     */
    @Override
    public Position askPosition() {
        int nbRow, nbColumn;
        System.out.println("could you insert the position of the animal please ?");
        do {
            System.out.println("input the row position number please! , the number must be positive !");
            nbRow = readNumber();
            System.out.println("input the column position number please! , the number must be positive !");
            nbColumn = readNumber();
        } while (nbRow < 0 || nbColumn < 0);
        return new Position(nbRow, nbColumn);
    }

    /**
     * Asking player for a new direction.
     *
     * @return the new Direction given by the player.
     */
    @Override
    public Direction askDirection() {
        Scanner sc = new Scanner(System.in);
        String direction;
        do {
            System.out.println("could you insert the direction please ? , you can put NORTH ,SOUTH ,EAST ,WEST");
            direction = sc.next().toUpperCase();
        } while (!direction.equals("NORTH")
                && !direction.equals("SOUTH")
                && !direction.equals("EAST")
                && !direction.equals("WEST"));

        switch (direction) {
            case "NORTH":
                return Direction.NORTH;
            case "SOUTH":
                return Direction.SOUTH;
            case "EAST":
                return Direction.EAST;
            default:
                return Direction.WEST;
        }

    }

    /**
     * Add the wall on the square.
     *
     * @param square the given square.
     * @param board the given board.
     */
    public void addWall(String[][] square, Board board) {
        for (int col = 0; col < square[0].length; col++) {
            if (board.getSquares()[0][col] != null) {
                if (board.getSquares()[0][col].hasWall(Direction.NORTH)) {
                    square[0][col] = "NORTH";
                } else {
                    square[0][col] = "EMPTYNORTH";
                }
            }
            if (board.getSquares()[board.getNbRow() - 1][col] != null) {
                if (board.getSquares()[board.getNbRow() - 1][col].hasWall(Direction.SOUTH)) {
                    square[square.length - 1][col] = "SOUTH";
                } else {
                    square[square.length - 1][col] = "EMPTYSOUTH";
                }
            }
        }

        for (int rowWall = 2, rowBoard = 0; rowWall < square.length - 1; rowWall += 3, rowBoard++) {
            for (int col = 0; col < square[0].length; col++) {
                if (board.getSquares()[rowBoard + 1][col] != null) {
                    if (board.getSquares()[rowBoard + 1][col].hasWall(Direction.NORTH)) {
                        square[rowWall + 1][col] = "NORTH";
                    } else {
                        square[rowWall + 1][col] = "EMPTYNORTH";
                    }
                }
                if (board.getSquares()[rowBoard][col] != null) {
                    if (board.getSquares()[rowBoard][col].hasWall(Direction.SOUTH)) {
                        square[rowWall][col] = "SOUTH";
                    } else {
                        square[rowWall][col] = "EMPTYSOUTH";
                    }
                }
            }

        }
    }

    /**
     * Fill the array of squares with the name of square or the name of the
     * animal.
     *
     * @param square the array of squares.
     * @param board the board of the game.
     * @param animals the animals on the game.
     */
    public void fillTheSquares(String[][] square, Board board, Animal... animals) {
        int boardRow = 0;
        for (int i = 1; boardRow < board.getNbRow(); i += 3) {
            for (int j = 0; j < board.getNbColumn(); j++) {

                if (board.isInside(new Position(boardRow, j))) {
                    switch (board.getSquareType(new Position(boardRow, j))) {
                        case GRASS:
                            square[i][j] = "GRASS";
                            for (Animal animal : animals) {
                                if (animal.getPositionOnBoard().equals(new Position(boardRow, j))) {
                                    if (!animal.isOnStar()) {
                                        square[i][j] = animal.toString();
                                    }
                                }
                            }
                            break;
                        case STAR:
                            square[i][j] = "STAR";
                            for (Animal animal : animals) {
                                if (animal.getPositionOnBoard().equals(new Position(boardRow, j))) {
                                    square[i][j] = "GRASS";
                                }
                            }
                            break;
                    }

                }
            }
            boardRow++;
        }
    }

    /**
     * Print the board of the game.
     *
     * @param square the array of squares which contain theirs String names.
     * @param board the board of the game.
     */
    public void printBoard(String[][] square, Board board) {
        for (int i = 0, row = 0; i < square.length; i++) {
            for (int j = 0; j < square[0].length; j++) {
                if (square[i][j] == null) {
                    System.out.print("            ");
                } else {
                    switch (square[i][j]) {
                        case "SNAIL":
                            printColor(board, row, j, "SNL", TerminalColor.BG_GREEN);
                            break;
                        case "SPIDER":
                            printColor(board, row, j, "SPR", TerminalColor.BG_GREEN);
                            break;
                        case "GRASSHOPPER":
                            printColor(board, row, j, "GHR", TerminalColor.BG_GREEN);
                            break;
                        case "LADYBIRD":
                            printColor(board, row, j, "LBD", TerminalColor.BG_GREEN);
                            break;
                        case "BUMBELBEE":
                            printColor(board, row, j, "BBE", TerminalColor.BG_GREEN);
                            break;
                        case "BUTTERFLY":
                            printColor(board, row, j, "BFY", TerminalColor.BG_GREEN);
                            break;
                        case "GRASS":

                            printColor(board, row, j, "   ", TerminalColor.BG_GREEN);

                            break;
                        case "STAR":
                            if (board.getSquares()[row][j].hasWall(Direction.EAST) && board.getSquares()[row][j].hasWall(Direction.WEST)) {
                                System.out.print(TerminalColor.BG_BLUE.toString() + "|" + "\033[m" + TerminalColor.BG_YELLOW.toString() + TerminalColor.RED.toString() + "    ***    " + "\033[m" + TerminalColor.BG_BLUE.toString() + "|" + "\033[m");
                            } else if (board.getSquares()[row][j].hasWall(Direction.EAST)) {
                                System.out.print(TerminalColor.BG_YELLOW.toString() + TerminalColor.RED.toString() + "[   ***    " + "\033[m" + TerminalColor.BG_BLUE.toString() + "|" + "\033[m");
                            } else if (board.getSquares()[row][j].hasWall(Direction.WEST)) {
                                System.out.print(TerminalColor.BG_BLUE.toString() + "|" + "\033[m" + TerminalColor.BG_YELLOW.toString() + TerminalColor.RED.toString() + "    ***   ]" + "\033[m");
                            } else {
                                System.out.print(TerminalColor.BG_YELLOW.toString() + TerminalColor.RED.toString() + "[    ***   ]" + "\033[m");
                            }
                            break;
                        case "NORTH":
                            System.out.print(TerminalColor.BG_BLUE.toString() + "____________" + "\033[m");
                            break;
                        case "SOUTH":
                            System.out.print(TerminalColor.BG_BLUE.toString() + "¯¯¯¯¯¯¯¯¯¯¯¯" + "\033[m");
                            break;
                        case "EMPTYNORTH":
                            if (square[i + 1][j].equals("STAR")) {
                                System.out.print(TerminalColor.BG_YELLOW.toString() + TerminalColor.RED.toString() + "[¯¯¯¯¯¯¯¯¯¯]" + "\033[m");
                            } else {
                                System.out.print(TerminalColor.BG_GREEN.toString() + "[¯¯¯¯¯¯¯¯¯¯]" + "\033[m");
                            }
                            break;
                        case "EMPTYSOUTH":
                            if (square[i - 1][j].equals("STAR")) {
                                System.out.print(TerminalColor.BG_YELLOW.toString() + TerminalColor.BG_YELLOW.toString() + TerminalColor.RED.toString() + "[__________]" + "\033[m");
                            } else {
                                System.out.print(TerminalColor.BG_GREEN.toString() + "[__________]" + "\033[m");
                            }
                            break;
                    }
                }

            }
            System.out.println("");
            if (i % 3 == 0 && i != 0) {
                row++;
            }
        }

    }

    /**
     * display the board and animals on the board of the game.
     *
     * @param board the given board to be displayed.
     * @param animals the given array of animals to be displayed.
     */
    @Override
    public void displayBoard(Board board, Animal... animals) {

        String[][] square = new String[board.getNbRow() * 3][board.getNbColumn()];
        addWall(square, board);
        fillTheSquares(square, board, animals);
        printBoard(square, board);
    }

    /**
     * Print the number of remaining moves.
     *
     * @param remainingMoves the given remaining moves.
     */
    @Override
    public void displayRemainingMoves(int remainingMoves) {
        System.out.println(TerminalColor.BLUE + "****you have " + remainingMoves + " try****" + "\033[m");
    }
}

//}
//    public static void main(String[] args) {
//        View view = new View();
//        Board board = new g54685.humbug.model.Board(new Square[][]{
//            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
//            {null, new Square(GRASS), new Square(GRASS), new Square(GRASS), new Square(GRASS)},
//            {new Square(GRASS), new Square(GRASS), new Square(STAR), new Square(GRASS), new Square(GRASS)}
//        });
//        Animal[] animals = {new Butterfly(new Position(0, 0)), new Spider(new Position(0, 2)), new Bumbelbee(new Position(0, 3))};
//        board.getSquares()[0][0].setNorthWall(true);
//        board.getSquares()[0][0].setEastWall(true);
//        board.getSquares()[0][0].setWestWall(true);
//        board.getSquares()[0][2].setSouthWall(true);
//         board.getSquares()[0][1].setEastWall(true);
//        board.getSquares()[0][1].setWestWall(true);
//        board.getSquares()[0][1].setNorthWall(true);
//        board.getSquares()[0][0].setSouthWall(true);
//         board.getSquares()[0][2].setEastWall(true);
//        board.getSquares()[0][2].setWestWall(true);
//        board.getSquares()[0][2].setNorthWall(true);
//        board.getSquares()[0][2].setSouthWall(true);
//         board.getSquares()[0][3].setEastWall(true);
//        board.getSquares()[0][3].setWestWall(true);
//        board.getSquares()[0][3].setNorthWall(true);
//        board.getSquares()[0][2].setSouthWall(true);
//        Position position = animals[0].move(board, Direction.EAST, animals);
//        animals[0].setPositionOnBoard(position);
//        view.displayBoard(board, animals);
//        Game game = new Game();
//        game.startLevel(2);
//        view.displayRemainingMoves(game.getRemainingMoves());
//        view.displayBoard(getLevel(3).getBoard(), getLevel(3).getAnimals());
//                Position position = getLevel(3).getAnimals()[0].move(getLevel(3).getBoard(), Direction.NORTH,getLevel(3).getAnimals());
//        System.out.println(position);
//        System.out.println(game.getRemainingMoves());
//    }
//}
