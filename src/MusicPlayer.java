package Diablo;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer extends Thread
{
    private AudioInputStream audioInputStream;
    private Long currentTime;
    private Clip clip;
    private String filePath;

    public MusicPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        this.filePath = filePath;
    }

    public void run()
    {
        try
        {
            System.out.println("Trying: " + filePath);
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            if(filePath.equals(Game.root + "/resources/music/runningStone.WAV") || filePath.equals(Game.root + "/resources/music/runningDirt.WAV"))
                pause();
            Thread.sleep(100);
            while(clip.isRunning())
            {
                Thread.sleep(100);
            }
        }catch(Exception ex){ex.printStackTrace();}
    }

    public void play()
    {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopSong()
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

    public void pauseAndReset()
    {
        currentTime = 0L;
        clip.stop();
    }

    public void resumeSong()
    {
        clip.setMicrosecondPosition(currentTime);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public boolean isRunning()
    {
        return clip.isRunning();
    }
}