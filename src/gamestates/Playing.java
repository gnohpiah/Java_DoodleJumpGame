package gamestates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import Audio.AudioPlayer;
import ConnectDB.ScoreCheckDA;
import Entity.*;
import main.Game;
import main.GamePanel;
import ui.MuteButton;
import ui.PauseButton;
import javax.imageio.ImageIO;

public class Playing extends State implements Statemethods{
    public static int lastScore = 0;
    public MovingPlatform movingPlatform;
    public int movePos;
    public Trampoline trampoline;
    public MuteButton muteButton;
    public int jumpPos;
    public Trap trap;
    public int trapPos1,trapPos2;
    public Platform platform;
    public Player player;
    public AudioPlayer audioPlayer;
    public PauseButton pauseButton;
    public PlatformPosition[] platformPositions;
    public BufferedImage background,view,badge;
    public float h = 150;
    public ScoreCheckDA sc;

    public Playing(Game game) {
        super(game);
        pauseButton = new PauseButton();
        muteButton = new MuteButton();
        audioPlayer = new AudioPlayer();
        movingPlatform = new MovingPlatform();
        trampoline = new Trampoline();
        trap = new Trap();
        platform = new Platform();
        pauseButton = new PauseButton();
        player = new Player();
        pauseButton = new PauseButton();
        muteButton = new MuteButton();
        sc = new ScoreCheckDA();
        start();
    }

    public void start(){
        try{
            initImg();
            do {
                trapPos1 = new Random().nextInt(12);
            } while(trapPos1 == 11);
            do {
                trapPos2 = new Random().nextInt(12);
            } while(trapPos2 == trapPos1 || trapPos2 == trapPos1 + 1 || trapPos2 == trapPos1 - 1 || trapPos2 == 11);
            do {
                jumpPos = new Random().nextInt(12);
            } while(jumpPos == trapPos1 || jumpPos == trapPos2 || jumpPos == 11);
            do {
                movePos = new Random().nextInt(12);
            } while(movePos == trapPos1 || movePos == trapPos2 || movePos == jumpPos || movePos == 11);

            platformPositions = new PlatformPosition[20];
            platformPositions[0] = new PlatformPosition();
            platformPositions[0].y = 0;
            platformPositions[0].x = new Random().nextInt(GamePanel.WIDTH-platform.getWidth());
            for(int i=1;i<12;i++){
                platformPositions[i] = new PlatformPosition();
                platformPositions[i].y = platformPositions[i-1].y + 60;
                platformPositions[i].x = new Random().nextInt(GamePanel.WIDTH-platform.getWidth());
            }
            player.setX(platformPositions[11].x);
            player.setY(platformPositions[11].y-50);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void initImg(){
        try {
            view = new BufferedImage(GamePanel.WIDTH,GamePanel.HEIGHT,BufferedImage.TYPE_INT_RGB);
            background = ImageIO.read(getClass().getResource("/background.png"));
            badge = ImageIO.read(getClass().getResource("/badge.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            Player.right = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            Player.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            Gamestate.state = Gamestate.MENU;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            Player.right = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            Player.left = false;
        }
    }

    @Override
    public void update() {
        lastScore = player.score;
        Player.updatePlayer(player);
        platformPositions[movePos].x = movingPlatform.updateMovingPos(platformPositions[movePos].x);
        if(player.getY() < h){
            player.score += Math.abs(player.getY()-h);
            player.setY(h);
            for(int i=0;i<12;i++){
                platformPositions[i].y = platformPositions[i].y - (int)player.fallingSpeed;
                if(platformPositions[i].y > GamePanel.HEIGHT){
                    platformPositions[i].y = 0;
                    platformPositions[i].x = new Random().nextInt(GamePanel.WIDTH-platform.getWidth());
                }
            }
        }
        trap.checkJump(player,platformPositions[trapPos1].x,platformPositions[trapPos1].y);
        trap.checkJump(player,platformPositions[trapPos2].x,platformPositions[trapPos2].y);
        trampoline.checkJump(player,platformPositions[jumpPos].x,platformPositions[jumpPos].y);
        movingPlatform.checkJump(player,platformPositions[movePos].x,platformPositions[movePos].y);
        for(int i=0;i<12;i++) {
            platform.checkJump(player,platformPositions[i].x,platformPositions[i].y);
        }
    }

    @Override
    public void draw(Graphics g) {
        if(!Player.isAlive){
            AudioPlayer.startDead();
            sc.insert(lastScore);
            player = new Player();
            Gamestate.state = Gamestate.MENU;
            start();
        }
        else{
            Graphics2D g2 = (Graphics2D)view.getGraphics();
            g2.drawImage(background,0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
            for(int i=0;i<12;i++){
                if(i == jumpPos) trampoline.drawPlat(g2,platformPositions[jumpPos].x,platformPositions[jumpPos].y);
                else if(i == trapPos1) trap.drawPlat(g2,platformPositions[i].x,platformPositions[i].y);
                else if(i == trapPos2) trap.drawPlat(g2,platformPositions[i].x,platformPositions[i].y);
                else if(i == movePos) continue;
                else platform.drawPlat(g2,platformPositions[i].x,platformPositions[i].y);
            }
            movingPlatform.drawPlat(g2,platformPositions[movePos].x,platformPositions[movePos].y);
            player.draw(g2);
            g.drawImage(view,0,0,GamePanel.WIDTH,GamePanel.HEIGHT,null);
            g.drawImage(badge,380,20,130,50,null);
            player.drawPoint(g);
            pauseButton.drawButton(g);
            muteButton.drawButton(g);
            g.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(PauseButton.buttonState == 1){
            Gamestate.state = Gamestate.MENU;
        }
        if(MuteButton.buttonState == 1){
            if(MuteButton.muteType == 1){
                AudioPlayer.closeAllSound();
                MuteButton.muteType = 0;
            }
            else{
                audioPlayer = new AudioPlayer();
                AudioPlayer.loopTheme();
                AudioPlayer.openAllSound();
                MuteButton.muteType = 1;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        PauseButton.buttonState = pauseButton.clickable(e);
        MuteButton.buttonState = muteButton.clickable(e);
    }

}
