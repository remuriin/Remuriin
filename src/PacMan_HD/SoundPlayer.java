package PacMan_HD;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer
{
    private Clip clip;
    private String soundPath;
    private long lastPlayTime = 0;

    public SoundPlayer(String path)
    {
        this.soundPath = path;

        try
        {
            URL soundURL = getClass().getResource(path);
            if (soundURL == null)
            {
                System.out.println("Sound not found: " + path);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } 
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    public void play(float volume)
    {
        if (clip != null)
        {
            clip.setFramePosition(0);
            applyVolume(clip, volume);
            clip.start();
        }
    }

    public void loop(float volume)
    {
        if (clip != null)
        {
            applyVolume(clip, volume);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop()
    {
        if (clip != null)
        {
            clip.stop();
        }
    }

    public boolean isRunning()
    {
        return clip != null && clip.isRunning();
    }

    public void playNewInstance(float volume)
    {
        new Thread(() -> {
            try
            {
                URL soundURL = getClass().getResource(soundPath);
                if (soundURL == null) return;

                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
                Clip newClip = AudioSystem.getClip();
                newClip.open(audioIn);
                applyVolume(newClip, volume);
                newClip.start();
            } 
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }).start();
    }

    public void playWithCooldown(int fps, int frameDelay, float volume)
    {
        long minDelayMs = (long)((frameDelay / (double)fps) * 1000);
        long now = System.currentTimeMillis();

        if (now - lastPlayTime >= minDelayMs)
        {
            playNewInstance(volume);
            lastPlayTime = now;
        }
    }

    /** Internal helper for volume conversion and safety */
    private void applyVolume(Clip c, float volume)
    {
        if (c == null) return;
        volume = Math.max(0.0f, Math.min(volume, 1.0f)); // clamp 0–1

        try
        {
            FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float)(Math.log10(Math.max(volume, 0.0001)) * 20);
            gainControl.setValue(dB);
        } 
        catch (Exception ignored)
        {
            // Some systems may not support volume control
        }
    }
}