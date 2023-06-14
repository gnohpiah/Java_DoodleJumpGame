package Entity;

import Audio.AudioPlayer;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MovingPlatform extends Platform{
    private int speed = 1;

    private BufferedImage movingPlatform;
    public MovingPlatform(){
        initPlatImg();
    }
    public void initPlatImg(){
        try {
            movingPlatform = ImageIO.read(getClass().getResource("/moving_platform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void drawPlat(Graphics g, int x, int y){
        g.drawImage(movingPlatform,x,y,movingPlatform.getWidth(), movingPlatform.getHeight(),null);
    }
    public int getWidth(){
        return movingPlatform.getWidth();
    }
    public int getHeight(){
        return movingPlatform.getHeight();
    }
    public int updateMovingPos(int x){
        x += this.speed;
        if(x + this.getWidth() >= GamePanel.WIDTH && x >= 0) speed = -speed;
        else if(x + this.getWidth() <= GamePanel.WIDTH && x <= 0) speed = -speed;
        return x;
    }
    public void checkJump(Player player,int x,int y){
        if ((player.getX() + player.getWidth() > x) &&
                (player.getX() < x + movingPlatform.getWidth()) &&
                (player.getY() + player.getHeight() > y - 10) &&
                (player.getY() + player.getHeight() < y + 10) &&
                (player.fallingSpeed > 0)
        ) {
            AudioPlayer.touchNormal();
            player.fallingSpeed = -10;
        }
    }
}
