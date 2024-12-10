import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioPlayer
{
    private Clip clip;
    private String status;
    private AudioInputStream audioInputStream;

    public SimpleAudioPlayer(String file) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    // plays sound once
    public void playOnce() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.close();
        resetAudioStream();
        clip.start(); // play sound
        status = "play";
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File("src\\Boom.wav").getAbsoluteFile());
        clip.open(audioInputStream);
    }

}

