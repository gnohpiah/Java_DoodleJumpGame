package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import gamestates.Menu;
import gamestates.Playing;

public class KeyboardInputs implements KeyListener {
    private Playing playing;
    private Menu menu;

    public KeyboardInputs(Playing playing,Menu menu) {
        this.playing = playing;
        this.menu = menu;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        playing.keyPressed(e);
        menu.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        playing.keyReleased(e);
        menu.keyReleased(e);
    }
}
