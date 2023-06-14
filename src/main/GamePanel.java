package main;

import java.awt.*;
import javax.swing.JPanel;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import inputs.KeyboardInputs;
import inputs.MouseInputs;


public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    public KeyboardInputs keyboardInputs;
    public static final int WIDTH = 540;
    public static final int HEIGHT = 720;
    private Game game;
    private Menu menu;
    private Playing playing;


    public GamePanel(Game game) {
        this.game = game;
        playing = new Playing(game);
        menu = new Menu(game);
        setPanelSize();
        mouseInputs = new MouseInputs(playing,menu);
        keyboardInputs = new KeyboardInputs(playing,menu);
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
    }
    public void update(){
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }
    }

    public void draw(Graphics g){
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

}