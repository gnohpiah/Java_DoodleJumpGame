package Entity;

import Audio.AudioPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Platform {
    private BufferedImage platform;
    public Platform(){
        initPlatImg();
    }
    public void initPlatImg(){
        try {
            platform = ImageIO.read(getClass().getResource("/platform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void drawPlat(Graphics g,int x,int y){
        g.drawImage(platform,x,y,platform.getWidth(), platform.getHeight(),null);
    }
    public int getWidth(){
        return platform.getWidth();
    }
    public int getHeight(){
        return platform.getHeight();
    }
    public void checkJump(Player player,int x,int y){
        if ((player.getX() + player.getWidth() > x) &&
                (player.getX() < x + platform.getWidth()) &&
                (player.getY() + player.getHeight() > y - 10) &&
                (player.getY() + player.getHeight() < y + 10) &&
                (player.fallingSpeed > 0)
        ) {
            AudioPlayer.touchNormal();
            player.fallingSpeed = -10;
        }
    }
}
