package utils;

import javax.sound.sampled.*;
import java.io.File;

public class SoundManager {
    private Clip clip;

    /** מנגן קובץ ברצף אינסופי */
    public void playLoop(String path) { play(path, true); }

    /** מנגן קובץ פעם אחת בלבד */
    public void playOnce(String path) { play(path, false); }

    private void play(String path, boolean loop) {
        stop();
        try (AudioInputStream in = AudioSystem.getAudioInputStream(new File(path))) {
            clip = AudioSystem.getClip();
            clip.open(in);
            if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) { e.printStackTrace(); }
    }

    /** עוצר ומפנה את הקליפ הנוכחי (אם יש) */
    public void stop() {
        if (clip != null) { clip.stop(); clip.close(); }
    }
}
