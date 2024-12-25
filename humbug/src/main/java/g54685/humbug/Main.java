package g54685.humbug;

import g54685.humbug.controller.Controller;
import g54685.humbug.model.Game;
import g54685.humbug.view.text.View;

/**
 * Allows to launched the game by the startGame method.
 *
 * @author Nader < 54685@etu.he2b.be >
 */
public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new Game(), new View());
        int level =controller.choiceLevel();
        controller.startGame(level);

    }  
}
