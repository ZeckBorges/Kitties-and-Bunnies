package GameProject.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Sound {

    private static Map<String, Sound> soundMap = new HashMap<>();

    private Clip clip;

    private Sound(AudioInputStream audioInputStream) throws LineUnavailableException, IOException {
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public static void load(String name, String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
            soundMap.put(name, new Sound(audioInputStream));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void play(String name) {
        Sound sound = soundMap.get(name);
        if (sound != null) {
            sound.play();
        }
    }

    public static void loop(String name) {
        Sound sound = soundMap.get(name);
        if (sound != null) {
            sound.loop();
        }
    }

    public static void stop(String name) {
        Sound sound = soundMap.get(name);
        if (sound != null) {
            sound.stop();
        }
    }

    private void play() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }

    private void loop() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stop() {
        clip.stop();
    }
}
