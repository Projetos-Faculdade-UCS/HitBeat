package hitbeat.controller.player;

import hitbeat.model.Queue;
import hitbeat.model.Track;
import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class PlayerController {
    private static PlayerController instance = null;
    private MediaPlayer song;
    private Node playerView;
    

    private PlayerController(Node playerView) {
        this.song = new MediaPlayer(null); //testar se isso funciona
        this.playerView = playerView;
    }

    public static PlayerController getInstance(Node playerView) {
        if (instance == null) {
            instance = new PlayerController(playerView);
        }
        return instance;
    }

    public boolean isPlaying() {
        return this.song.getStatus() == Status.PLAYING;
    }

    public void play() {
    }

    public void play(Track track) {
        this.song.stop();
        this.song.dispose();
        this.song = new MediaPlayer( new Media(track.getFilePath()) );
        this.song.play();
    }

    public void play(Queue queue){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // private void set
}
