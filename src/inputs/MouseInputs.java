package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private Playing playing;
    private Menu menu;

    public MouseInputs(Playing playing,Menu menu) {
        this.playing = playing;
        this.menu = menu;

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                menu.mouseMoved(e);
                break;
            case PLAYING:
                playing.mouseMoved(e);
                break;
            default:
                break;

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.state) {
            case MENU:
                menu.mouseClicked(e);
                break;
            case PLAYING:
                playing.mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
