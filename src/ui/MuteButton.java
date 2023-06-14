package ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MuteButton implements Button{
    public static int buttonState;
    public static int muteType;
    private BufferedImage sound,mute;
    public MuteButton(){
        initButton();
        muteType = 1;
    }

    public void initButton(){
        try {
            sound = ImageIO.read(getClass().getResource("/sound.png"));
            mute = ImageIO.read(getClass().getResource("/mute.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void drawButton(Graphics g){
        if(muteType == 1){
            g.drawImage(sound,80,20,40, 40,null);
        }
        else{
            g.drawImage(mute,80,20,40, 40,null);
        }
    }
    public int clickable(MouseEvent e){
        if(e.getX() >= 80 && e.getX() <= 80 + 40 && e.getY() >= 20 && e.getY() <= 20 + 40) {
            return 1;
        }
        else{
            return 0;
        }
    }
}
