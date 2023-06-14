package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Trap extends Platform{
    private BufferedImage trap;
    public Trap(){
        initPlatImg();
    }
    public void initPlatImg(){
        try {
            trap = ImageIO.read(getClass().getResource("/trap.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void drawPlat(Graphics g, int x, int y){
        g.drawImage(trap,x,y,trap.getWidth(), trap.getHeight(),null);
    }
    public int getWidth(){
        return trap.getWidth();
    }
    public int getHeight(){
        return trap.getHeight();
    }
    public void checkJump(Player player,int x,int y){
        if ((player.getX() + player.getWidth() > x) &&
                (player.getX() < x + trap.getWidth()) &&
                (player.getY() + player.getHeight() > y - 10) &&
                (player.getY() + player.getHeight() < y + 10) &&
                (player.fallingSpeed > 0)
        ) {
            Player.isAlive = false;
        }
    }
}
