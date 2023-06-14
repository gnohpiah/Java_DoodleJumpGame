package Entity;

import Audio.AudioPlayer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Trampoline extends Platform{
    private BufferedImage trampoline;
    public Trampoline(){
        initPlatImg();
    }
    public void initPlatImg(){
        try {
            trampoline = ImageIO.read(getClass().getResource("/trampoline.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void drawPlat(Graphics g, int x, int y){
        g.drawImage(trampoline,x,y,trampoline.getWidth(), trampoline.getHeight(),null);
    }
    public int getWidth(){
        return trampoline.getWidth();
    }
    public int getHeight(){
        return trampoline.getHeight();
    }
    public void checkJump(Player player,int x,int y){
        if ((player.getX() + player.getWidth() > x) &&
                (player.getX() < x + trampoline.getWidth()) &&
                (player.getY() + player.getHeight() > y - 10) &&
                (player.getY() + player.getHeight() < y + 10) &&
                (player.fallingSpeed > 0)
        ) {
            AudioPlayer.touchTrampoline();
            player.fallingSpeed = -14;
        }
    }
}
