package Diablo;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer
{
    private AudioInputStream audioInputStream;
    private Long currentTime;
    private Clip clip;
    private String filePath;

    public MusicPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        this.filePath = filePath;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play()
    {
        clip.start();
    }

    public void stop()
    {
        currentTime = 0L;
        clip.stop();
        clip.close();
    }

    public void pause()
    {
        currentTime = clip.getMicrosecondPosition();
        clip.stop();
    }

    public void resume()
    {
        clip.setMicrosecondPosition(currentTime);
        clip.start();
    }
}