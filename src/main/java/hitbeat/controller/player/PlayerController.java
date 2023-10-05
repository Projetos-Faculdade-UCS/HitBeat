package hitbeat.controller.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hitbeat.dao.TrackDAO;
import hitbeat.model.Genre;
import hitbeat.model.Queue;
import hitbeat.model.Track;
import hitbeat.view.Player.ProgressBar;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class PlayerController {
    private static PlayerController instance;
    private MediaPlayer song;
    private Track track;
    private List<Runnable> onReadyActions = new ArrayList<>();
    // private List<Track> queue = new ArrayList<>();
    
    private PlayerController() {
    }

    public static PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }

    public boolean isPlaying() {
        if (!this.hasSong()) return false;
        return this.song.getStatus() == Status.PLAYING;
    }

    public boolean hasSong() {
        return this.song != null;
    }

    public void setOnPlay(Runnable action) {
        addOnReady(() -> {
            this.song.setOnPlaying(() -> action.run() );
        });
    }

    public void setOnPause(Runnable action) {
        addOnReady(() -> {
            this.song.setOnPaused(() -> action.run() );
        });
    }

    public void playPause() {
        if (!this.hasSong()) return;

        if (this.isPlaying()){
            this.song.pause();
        } else {
            this.song.play();
        }
    }

    public void play(Genre genre) {
        TrackDAO trackDAO = new TrackDAO();
        List<Track> tracks = trackDAO.filter(
            new HashMap<>() {{
                put("genre", genre);
            }}
        );
        this.play(tracks.get(0));
    }

    public void play(Track track) {
        this.dispose();

        this.track = track;
        this.song = new MediaPlayer( new Media(track.getFilePath()) );
        this.attach();
    }

    public void play(Queue queue){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*
    * Faz musica voltar ao inicio
    */
    public void resetSong(){
        if (!this.hasSong()) return;

        this.song.seek(this.song.getStartTime());
    }

    /*
    * Faz musica voltar ao inicio e tocar, toda vez que ela terminar
    */
    public void toggleRepeat() {
        if (!this.hasSong()) return;

        if (this.song.getCycleCount() == MediaPlayer.INDEFINITE) {
            // Desativa a repetição
            this.song.setCycleCount(1);
        } else {
            // Ativa a repetição infinita
            this.song.setCycleCount(MediaPlayer.INDEFINITE);
        }
    }

    // public void setOnReady(Runnable action) {
    //     this.song.setOnReady(action);
    // } 

    private void dispose() {
        if (!this.hasSong()) return;

        this.song.stop();
        // this.onReady = this.song.getOnReady();
        this.song.dispose();
    }

    private void attach() {
        this.song.setOnReady(() -> {
            this.executeOnReadyActions();
        });
        this.playPause();
    }

    public void seek(double sTime) {
        if (!this.hasSong()) return;
        this.song.seek(Duration.seconds(sTime));
    }

    public Timeline getProgressManager(ProgressBar ProgressBar) {
        Timeline progressManager = new Timeline(
            new KeyFrame(Duration.seconds(.01), event -> {
                if (!this.hasSong()) return;
                ProgressBar.setProgressIndicators(
                    this.song.getTotalDuration().toSeconds(),
                    this.song.getCurrentTime().toSeconds()
                );
            })
        );

        return progressManager;
    }

    public void bindVolume(DoubleProperty sliderValue) {
        addOnReady(() -> {
            this.song.volumeProperty().bind(sliderValue);
        });
    }

    /*
     * Adiciona uma ação a ser executada quando a musica estiver pronta.
     * Concatena a ação passada com a ação que já estava sendo executada.
     * @param action Ação a ser executada
     * @return void
     */
    public void addOnReady(Runnable action) {
        onReadyActions.add(action);
    }

    private void executeOnReadyActions() {
        for (Runnable action : onReadyActions) {
            action.run();
        }
    }
}
