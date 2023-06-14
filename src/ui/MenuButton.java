package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuButton implements Button{
    public static int buttonState;
    private BufferedImage play,play_hover;
    public MenuButton(){
        initButton();
    }

    public void initButton(){
        try {
            play = ImageIO.read(getClass().getResource("/play.png"));
            play_hover = ImageIO.read(getClass().getResource("/play_hover.png"));
            buttonState = 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void drawButton(Graphics g){
        if(buttonState == 1){
            g.drawImage(play_hover,147,250,250, 150,null);
        }
        else{
            g.drawImage(play,147,250,250, 150,null);
        }
    }
    public int clickable(MouseEvent e){
        if(e.getX() >= 147 && e.getX() <= 147 + 250 && e.getY() >= 250 && e.getY() <= 250 + 150) {
            return 1;
        }
        else{
            return 0;
        }
    }
}
