package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class  PauseButton implements Button{

    public static int buttonState;
    private BufferedImage pause;
    public PauseButton(){
        initButton();
    }

    public void initButton(){
        try {
            pause = ImageIO.read(getClass().getResource("/pause.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void drawButton(Graphics g){
        g.drawImage(pause,20,20,40, 40,null);

    }
    public int clickable(MouseEvent e){
        if(e.getX() >= 20 && e.getX() <= 20 + 40 && e.getY() >= 20 && e.getY() <= 20 + 40) {
            return 1;
        }
        else{
            return 0;
        }
    }
}
