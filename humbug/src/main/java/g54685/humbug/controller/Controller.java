package g54685.humbug.controller;

import g54685.humbug.model.Animal;
import g54685.humbug.model.LevelStatus;
import g54685.humbug.model.Model;
import g54685.humbug.view.text.InterfaceView;
import g54685.humbug.view.text.TerminalColor;
import g54685.humbug.view.text.View;
import java.util.Scanner;

/**
 * aAllows to make the link between the different methods created in the
 * project, it contains the method that allows to launch the game.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Controller {
    private Model game;
    private InterfaceView view;

    public Controller(Model game, InterfaceView view) {
        this.game = game;
        this.view = view;
    }

    /**
     * Checks if the animal fall on the kill zone..
     *
     * @param animals the given animals.
     * @return true if the animal's position was null , otherwise return false.
     */
    public boolean isFall(Animal... animals) {
        for (Animal animal : animals) {
            if (animal.getPositionOnBoard() == null) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * allows to choice the wanted level
     * @return the wanted level.
     */
    public int choiceLevel(){
         System.out.println("can you put the level you want to play ");
      int level = View.readNumber();
       while(  level  < 1 || level > 40 && level != 100 ){
           System.out.println("the level must be between 1 and 40 \nif you want you can play the last level 100 :)");
           level = View.readNumber();
       }
       return level;
    }
   

    /**
     * play a level and return the result.
     *
     * @param nLevel the given level.
     * @return true if the player have win , otherwise return false.
     */
    public boolean playLevel(int nLevel) {
        game.startLevel(nLevel);
        while (game.getLevelStatus().equals(LevelStatus.NOT_STARTED) || game.getLevelStatus().equals(LevelStatus.IN_PROGRESS)) {
            System.out.println("        " + TerminalColor.BLUE + "level: " + nLevel + "\033[m");
            view.displayRemainingMoves(game.getRemainingMoves());
            view.displayBoard(game.getBoard(), game.getAnimals());
            try {
                game.move(view.askPosition(), view.askDirection());
            } catch (Exception e) {
                view.displayError("Error when the animal moves!");
            }
            if (isFall(game.getAnimals())) {
                System.out.println(TerminalColor.RED+"Sorry the animal fall on the kill zone !! , try again :)");
                return false;
            }
            System.out.println("________________________________________________________________");

        }
        System.out.println(TerminalColor.BLUE + "the current level is the level number " + nLevel + "\033[m");
        view.displayRemainingMoves(game.getRemainingMoves());
        view.displayBoard(game.getBoard(), game.getAnimals());
        System.out.println("****************************************************************");

        return game.getLevelStatus().equals(LevelStatus.WIN);
    }
    
    /**
     * Allows to launched the game.
     *
     * @param nLevel the given level to play.
     */
    public void startGame(int nLevel) {
        boolean exit = false;
        if (!exit) {
            boolean win = playLevel(nLevel);

            if (win) {
                if (nLevel == 40){
                    System.out.println(TerminalColor.RED +"you can try to play the last level! :)\033[m");
                    startGame(100);
                }
                if(nLevel == 100){
                    exit = true;
                    System.out.println(TerminalColor.RED +"congratulation you have finished the last level !" + "\033[m");
                }else{
                    startGame(nLevel+1);
                }
               
            }else{
                startGame(nLevel);
            }

            
        }
    }
}
