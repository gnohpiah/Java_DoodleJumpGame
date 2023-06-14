package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import ConnectDB.ScoreCheckDA;
import Entity.Player;
import main.Game;
import ui.MenuButton;
import javax.imageio.ImageIO;

public class Menu extends State implements Statemethods{
    private BufferedImage background_menu,menu_wall,badge;
    private MenuButton menuButton;
    public ScoreCheckDA sc;
    public Menu(Game game) {
        super(game);
        sc = new ScoreCheckDA();
        this.menuButton = new MenuButton();
        this.initImg();
    }

    public void initImg(){
        try {
            background_menu = ImageIO.read(getClass().getResource("/menu_background.png"));
            menu_wall = ImageIO.read(getClass().getResource("/menu_wall.png"));
            badge = ImageIO.read(getClass().getResource("/badge.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menu_wall,0,0,menu_wall.getWidth(), menu_wall.getHeight(),null);
        g.drawImage(background_menu,45,50,450, 650,null);
        g.drawImage(badge,100,420,340, 220,null);
        menuButton.drawButton(g);
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("CustomFont/2DPixel.ttf")).deriveFont(22f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        g.setFont(font);
        g.setColor(new Color(76, 82, 64));
        if(Player.countPlays == 1){
            g.drawString(String.format("Highest score"),120,500);
            g.drawString(String.format("%d",sc.highestScore()),230,530);
        }
        else{
            if(Playing.lastScore >= sc.highestScore()){
                g.drawString(String.format("New high score"),118,500);
                g.drawString(String.format("%d",sc.highestScore()),230,530);
            }
            else{
                g.drawString(String.format("Your score"),150,493);
                g.drawString(String.format("%d",Playing.lastScore),230,523);
                g.drawString(String.format("High score"),150,558);
                g.drawString(String.format("%d",sc.highestScore()),230,588);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if(MenuButton.buttonState == 1){
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        MenuButton.buttonState = menuButton.clickable(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            Gamestate.state = Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}
