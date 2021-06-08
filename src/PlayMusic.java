import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlayMusic {

    public static Clip clip;
    public static int stateOfSlider = 10;
    public static int reduceVolume;

    static {
        reduceVolume = 0;
    }

    public static String playMusic(String game) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        String musicPath;


        switch (game) {
            case "Nuta #1": {
                musicPath = System.getProperty("user.dir") + "\\music\\Tentin_Quarantino_Day_1.wav";
                break;
            }
            case "Nuta #2": {
                musicPath = System.getProperty("user.dir") + "\\music\\2_Tygodnie_do_matury.wav";
                break;
            }
            case "Nuta #3": {
                musicPath = System.getProperty("user.dir") + "\\music\\Lonely_Night_In_Kalina.wav";
                break;
            }
            case "Nuta #4": {
                musicPath = System.getProperty("user.dir") + "\\music\\Jaki_zastosujemy_rodzaj_dystrybucji_przy_sprzedazy_pieczywa.wav";
                break;
            }
            default:
                musicPath = System.getProperty("user.dir") + "\\music\\Tentin_Quarantino_Day_1.wav";
        }

        File musicFile = new File(musicPath);

        if (musicFile.exists()) {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);

            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        return musicPath;

    }

    public static void ChangeVolume(int value) {
        reduceVolume = 10 - value;
        stateOfSlider = value;

        if (value == 0) reduceVolume = 200;


        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-(float) (reduceVolume * 5));
    }

    public static int returnReduceVolume() {
        return reduceVolume;
    }

}
