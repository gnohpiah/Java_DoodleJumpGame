package Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private static Clip theme,normal_platform,dead,trampoline_jump;
    public AudioPlayer(){
        try {
            theme = AudioPlayer.getSound("Sound/theme.wav");
            normal_platform = AudioPlayer.getSound("Sound/normal_platform.wav");
            dead = AudioPlayer.getSound("Sound/dead.wav");
            trampoline_jump = AudioPlayer.getSound("Sound/trampoline_jump.wav");
            openAllSound();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }

    }

    public static void openAllSound(){
        try {
            if (!theme.isOpen()) {
                theme.open();
            }
            if (!normal_platform.isOpen()) {
                normal_platform.open();
            }
            if (!dead.isOpen()) {
                dead.open();
            }
            if (!trampoline_jump.isOpen()) {
                trampoline_jump.open();
            }
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeAllSound() {
        if (theme != null && theme.isOpen()) {
            theme.stop();
            theme.close();
        }
        if (normal_platform != null && normal_platform.isOpen()) {
            normal_platform.stop();
            normal_platform.close();
        }
        if (dead != null && dead.isOpen()) {
            dead.stop();
            dead.close();
        }
        if (trampoline_jump != null && trampoline_jump.isOpen()) {
            trampoline_jump.stop();
            trampoline_jump.close();
        }
    }


    public static Clip getSound(String soundFile) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File(soundFile);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        return clip;
    }

    public static void loopTheme(){
        if(theme.isRunning()){
            theme.stop();
        }
        theme.setFramePosition(0);
        theme.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void touchNormal(){
        if(normal_platform.isRunning()){
            normal_platform.stop();
        }
        normal_platform.setFramePosition(0);
        normal_platform.start();
    }

    public static void touchTrampoline(){
        if(trampoline_jump.isRunning()){
            trampoline_jump.stop();
        }
        trampoline_jump.setFramePosition(0);
        trampoline_jump.start();
    }

    public static void startDead(){
        if(dead.isRunning()){
            dead.stop();
        }
        dead.setFramePosition(0);
        dead.start();
    }
}
