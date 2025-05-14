package utils;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {
    private Clip clip;

    /**
     * מנגן קובץ ברצף אינסופי
     */
    public void playLoop(String path) {
        play(path, true);
    }

    public void makeAShot(String path) {
        new Thread(() -> {                   // keep I/O off the EDT
            try (AudioInputStream in =
                         AudioSystem.getAudioInputStream(new File(path))) {

                Clip sfx = AudioSystem.getClip();
                sfx.open(in);
                sfx.start();

                /* Free native resources when the sound is done */
                sfx.addLineListener(ev -> {
                    if (ev.getType() == LineEvent.Type.STOP) {
                        sfx.close();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void playOnce(String path) {
        play(path, false);
    }

    private void play(String path, boolean loop) {
        stop();
        try (AudioInputStream in = AudioSystem.getAudioInputStream(new File(path))) {
            clip = AudioSystem.getClip();
            clip.open(in);
            if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * עוצר ומפנה את הקליפ הנוכחי (אם יש)
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
