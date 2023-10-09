package hitbeat.controller.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import hitbeat.dao.TrackDAO;
import hitbeat.model.Genre;
import hitbeat.model.Queue;
import hitbeat.model.Track;
import javafx.beans.property.DoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class PlayerController {

    private static PlayerController instance;
    private MediaPlayer song;
    private Track track;
    private List<Consumer<MediaPlayer>> onReadyActions = new ArrayList<>();
    private boolean repeat = false;
    private Consumer<Boolean> onRepeat;
    private Consumer<Status> onStatusChange;

    private PlayerController() {
    }

    public static PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
        }
        return instance;
    }

    // Playback Control Methods

    public void playPause() {
        if (this.hasSong()) {
            if (this.isPlaying()) {
                song.pause();
            } else {
                song.play();
            }
        }
    }

    public void play(Genre genre) {
        TrackDAO trackDAO = new TrackDAO();
        List<Track> tracks = trackDAO.filter(new HashMap<>() {
            {
                put("genre", genre);
            }
        });
        this.play(tracks.get(0));
    }

    public void play(Track track) {
        disposeCurrentSong();
        this.track = track;
        this.song = new MediaPlayer(new Media(track.getFilePath()));
        attachSongListeners();
        playPause();
    }

    public void play(Queue queue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void resetSong() {
        if (this.hasSong())
            song.seek(song.getStartTime());
    }

    public void toggleRepeat() {
        setRepeat(!repeat);
        addOnReady((s) -> setRepeat(repeat));
    }

    public void seek(double sTime) {
        if (this.hasSong())
            song.seek(Duration.seconds(sTime));
    }

    // Listener Setup
    public void setOnReady(Consumer<MediaPlayer> action) {
        addOnReady(action);
    }

    public void setOnPlay(Runnable action) {
        addOnReady((s) -> s.setOnPlaying(action));
    }

    public void setOnPause(Runnable action) {
        addOnReady((s) -> s.setOnPaused(action));
    }

    public void setOnRepeat(Consumer<Boolean> action) {
        this.onRepeat = action;
    }

    public void bindVolume(DoubleProperty sliderValue) {
        addOnReady((s) -> s.volumeProperty().bind(sliderValue));
    }

    public void setOnProgress(Consumer<Double> action) {
        addOnReady((s) -> s.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                action.accept(newValue.toSeconds());
            }
        }));
    }

    public void setOnStatusChange(Consumer<Status> action) {
        this.onStatusChange = action;
        addOnReady((s) -> s.statusProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                action.accept(newValue);
            }
        }));
    }

    // Utility Methods

    public boolean isPlaying() {
        return this.hasSong() && song.getStatus() == Status.PLAYING;
    }

    public boolean hasSong() {
        return song != null;
    }

    public Track getTrack() {
        return this.track;
    }

    // Private Helper Methods

    private void setRepeat(boolean repeat) {
        this.repeat = repeat;
        Optional.ofNullable(onRepeat).ifPresent(action -> action.accept(this.repeat));
        if (this.hasSong())
            song.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
    }

    private void attachSongListeners() {
        song.setOnEndOfMedia(() -> {
            if (onStatusChange != null) {
                onStatusChange.accept(Status.STOPPED);
            }
        });
        song.setOnReady(this::executeOnReadyActions);
    }

    private void disposeCurrentSong() {
        if (this.hasSong()) {
            song.stop();
            song.volumeProperty().unbind();
            song.setOnReady(null);
            song.setOnPlaying(null);
            song.setOnPaused(null);
            song.setOnEndOfMedia(null);
            song.setOnStopped(null);
            song.setOnHalted(null);
            song.setOnError(null);
            song.dispose();
        }
    }

    private void addOnReady(Consumer<MediaPlayer> action) {
        onReadyActions.add(action);
    }

    private void executeOnReadyActions() {
        onReadyActions.forEach(action -> action.accept(song));
    }
}
