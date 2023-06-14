package Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
    public static int countPlays = 0;
    public float x,y;
    public int score;
    public double fallingSpeed = 0,fallingAcceleration = 0.2;
    private BufferedImage img_left,img_right;
    public static boolean right,left,isAlive;

    public Player(){
        countPlays++;
        initImg();
        isAlive = true;
        score = 0;
    }
    public void draw(Graphics2D g2){
        if(left){
            g2.drawImage(img_left,(int)x,(int)y,img_left.getWidth(), img_left.getHeight(),null);
        }
        else{
            g2.drawImage(img_right,(int)x,(int)y,img_right.getWidth(), img_right.getHeight(),null);
        }
    }
    public static void updatePlayer(Player player){
        if(Player.right){
            player.x += 3;
        }
        else if(Player.left){
            player.x -= 3;
        }
        if(player.x > GamePanel.WIDTH) player.x = 0;
        else if(player.x < 0-30) player.x = GamePanel.WIDTH;
        player.fallingSpeed += player.fallingAcceleration;
        player.y += player.fallingSpeed;
        if(player.y > GamePanel.HEIGHT){
            Player.isAlive = false;
        }
    }
    public void initImg(){
        try {
            img_left = ImageIO.read(getClass().getResource("/doodle_left.png"));
            img_right = ImageIO.read(getClass().getResource("/doodle_right.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void drawPoint(Graphics g){
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("CustomFont/2DPixel.ttf")).deriveFont(14f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        g.setFont(font);
        g.setColor(new Color(38, 42, 38));
        g.drawString(String.format("%d",(int)score),400,52);
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    public int getWidth(){
        return img_left.getWidth();
    }
    public int getHeight(){
        return img_left.getHeight();
    }
}
