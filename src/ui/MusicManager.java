package ui;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;




public class MusicManager{

    private String name;
    String[] playList  = {"track.mp3","track2.mp3","track3.mp3","track4.mp3"};
    MediaPlayer mediaPlayer;
    boolean muted = false;


    public void playMusic()
    {
        for(int i=0;i< playList.length;i++) {
            Media hit = new Media(new File(playList[i]).toURI().toString());
            mediaPlayer = new MediaPlayer(hit);
        }

        mediaPlayer.play();
    }
    public void setMuted(){

            mediaPlayer.setMute(true);

    }
    public void setUnmuted()
    {
        mediaPlayer.setMute(false);
    }


}