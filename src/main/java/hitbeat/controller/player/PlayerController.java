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

    private Runnable onReady;

    
    private PlayerController() {
        this.song = new MediaPlayer(null); //testar se isso funciona
    }

    public static PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }

    public boolean isPlaying() {
        return this.song.getStatus() == Status.PLAYING;
    }

    public void playPause() {
        if (this.isPlaying()){
            this.song.pause();
        } else {
            this.song.play();
        }
    }

    public void play(Track track) {
        this.song.stop();
        this.dispose();
        this.song = new MediaPlayer( new Media(track.getFilePath()) );
        this.attach();
        this.song.play();
    }

    public void play(Queue queue){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    * Faz musica voltar ao inicio
    */
    public void resetSong(){
        this.song.seek(this.song.getStartTime());
    }

    /*
    * Faz musica voltar ao inicio e tocar
    */
    public void toggleRepeat() {
        if (this.song.getCycleCount() == MediaPlayer.INDEFINITE) {
            // Desativa a repetição
            this.song.setCycleCount(1);
        } else {
            // Ativa a repetição infinita
            this.song.setCycleCount(MediaPlayer.INDEFINITE);
        }
    }

    public void setOnReady(Runnable action) {
        this.song.setOnReady(action);
    } 

    public double getTotalDuration() {
        return this.song.getTotalDuration().toSeconds();
    }

    public double getCurrentTime() {
        return this.song.getCurrentTime().toSeconds();
    }

    public void dispose() {
        this.onReady = this.song.getOnReady();
        this.song.dispose();
    }

    public void attach() {
        this.song.setOnReady(this.onReady);
    }

    public void seek(double sTime) {
        this.song.seek( sTime);
    }
    // private void set
}
