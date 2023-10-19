package hitbeat.controller.player;

import java.util.HashMap;
import java.util.List;

import hitbeat.dao.TrackDAO;
import hitbeat.model.Genre;
import hitbeat.model.Queue;
import hitbeat.model.Track;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import javafx.beans.property.DoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

// ... [other imports]

public class PlayerController {

    private static class SingletonHelper {
        private static final PlayerController INSTANCE = new PlayerController();
    }

    public class Progress {
        private double currentTime;

        public Progress(double currentTime) {
            this.currentTime = currentTime;
        }

        public double getCurrentTime() {
            return currentTime;
        }
    }

    public class SongStart {
        private Duration duration;

        private Track track;

        public SongStart(Duration duration) {
            this.duration = duration;
            this.track = PlayerController.this.getTrack();
        }

        public Duration getDuration() {
            return duration;
        }

        public Track getTrack() {
            return track;
        }
    }

    private MediaPlayer song;
    private Track track;
    private DoubleProperty volume = null;
    private BehaviorSubject<MediaPlayer.Status> songStatusSubject = BehaviorSubject.create();
    private PublishSubject<Track> playTrackSubject = PublishSubject.create();
    private boolean repeat = false;
    private BehaviorSubject<Boolean> repeatStatusSubject = BehaviorSubject.create();
    private BehaviorSubject<Progress> progressSubject = BehaviorSubject.create();
    private BehaviorSubject<SongStart> songStartSubject = BehaviorSubject.create();
    private BehaviorSubject<Double> volumeChangedSubject = BehaviorSubject.create();

    private PlayerController() {
        initializeSubscriptions();
    }

    private void initializeSubscriptions() {
        playTrackSubject
                .doOnNext(track -> disposeCurrentSong())
                .map(this::createMediaPlayerForTrack)
                .subscribe(mediaPlayer -> {
                    song = mediaPlayer;
                    songStatusSubject.onNext(mediaPlayer.getStatus());
                    attachSongListeners();
                    song.play();
                });

        repeatStatusSubject.subscribe(repeatStatus -> {
            if (hasSong()) {
                song.setCycleCount(repeatStatus ? MediaPlayer.INDEFINITE : 1);
            }
        });
    }

    private MediaPlayer createMediaPlayerForTrack(Track track) {
        this.track = track;
        return new MediaPlayer(new Media(track.getFilePath()));
    }

    public static PlayerController getInstance() {
        return SingletonHelper.INSTANCE;
    }

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
        playTrackSubject.onNext(tracks.get(0));
    }

    public void play(Track track) {
        playTrackSubject.onNext(track);
    }

    public void play(Queue queue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void resetSong() {
        if (this.hasSong())
            song.seek(song.getStartTime());
    }

    public void toggleRepeat() {
        repeat = !repeat;
        repeatStatusSubject.onNext(repeat);
    }

    public void seek(double sTime) {
        if (this.hasSong())
            song.seek(Duration.seconds(sTime));
    }

    public void setOnPlay(Runnable action) {
        if (hasSong())
            song.setOnPlaying(action);
    }

    public void setOnPause(Runnable action) {
        if (hasSong())
            song.setOnPaused(action);
    }

    public void setOnRepeat(Consumer<Boolean> action) {
        repeatStatusSubject.subscribe(action);
    }

    public void bindVolume(DoubleProperty sliderValue) {
        volume = sliderValue;
        if (hasSong())
            song.volumeProperty().bind(sliderValue);
    }

    public void setOnProgress(Consumer<Progress> action) {
        progressSubject.subscribe(action);
    }

    public void setOnSongStart(Consumer<SongStart> action) {
        songStartSubject.subscribe(action);
    }

    public void setOnStatusChange(Consumer<Status> action) {
        songStatusSubject.subscribe(action);
    }

    public void setOnVolumeChange(Consumer<Double> action) {
        volumeChangedSubject.subscribe(action);
    }

    public boolean isPlaying() {
        return this.hasSong() && songStatusSubject.getValue() == Status.PLAYING;
    }

    public boolean hasSong() {
        return song != null;
    }

    public Track getTrack() {
        return this.track;
    }

    private void disposeCurrentSong() {
        if (this.hasSong()) {
            song.stop();
            song.volumeProperty().unbind();
            song.dispose();
            song = null;
            track = null;
        }
    }

    private void attachSongListeners() {
        song.setOnEndOfMedia(() -> {
            if (!repeat)
                song.stop();
        });

        if (volume != null)
            song.volumeProperty().bind(volume);

        song.setOnReady(() -> {
            SongStart songStart = new SongStart(song.getTotalDuration());
            songStartSubject.onNext(songStart);
        });

        song.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double currentTime = newValue.toSeconds();

            Progress progress = new Progress(currentTime);
            progressSubject.onNext(progress);
        });

        song.statusProperty().addListener((observable, oldValue, newValue) -> {
            songStatusSubject.onNext(newValue);
        });
    }
}
