package ui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.nio.file.Paths;


public class MusicManager{

    private String name;
    MediaPlayer mediaPlayer;
    boolean muted = false;


    public void playMusic()
    {
        if(!muted) {
            Media hit = new Media(Paths.get("src/track.mp3").toUri().toString());
            mediaPlayer = new MediaPlayer(hit);


            mediaPlayer.play();
        }
    }
    public void mute(){

            mediaPlayer.setMute(true);

    }
    public void unmute()
    {
        mediaPlayer.setMute(false);
    }


}
