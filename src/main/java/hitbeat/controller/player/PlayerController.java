package hitbeat.controller.player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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
import javafx.beans.value.ChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

// ... [other imports]

public class PlayerController {

    // Singleton pattern implementation
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

        public SongStart(Duration duration) {
            this.duration = duration;
        }

        public Duration getDuration() {
            return duration;
        }

        public Track getTrack() {
            return PlayerController.this.getTrack();
        }
    }

    // Private member variables
    private MediaPlayer song;
    private Track currentTrack;
    private final Deque<Track> playbackQueue = new ArrayDeque<>();
    private final Deque<Track> playedTracks = new ArrayDeque<>();
    private DoubleProperty volume = null;
    private final BehaviorSubject<MediaPlayer.Status> songStatusSubject = BehaviorSubject.create();
    private final PublishSubject<Track> playTrackSubject = PublishSubject.create();
    private RepeatMode repeat = RepeatMode.NONE;
    private final BehaviorSubject<RepeatMode> repeatStatusSubject = BehaviorSubject.create();
    private final BehaviorSubject<Progress> progressSubject = BehaviorSubject.create();
    private final BehaviorSubject<SongStart> songStartSubject = BehaviorSubject.create();
    private final BehaviorSubject<Double> volumeChangedSubject = BehaviorSubject.create();

    // Disposable listeners
    private Runnable endOfMediaListener;
    private Runnable onReadyListener;
    private ChangeListener<? super Duration> currentTimeListener;
    private ChangeListener<? super MediaPlayer.Status> statusListener;

    // Private constructor for Singleton
    private PlayerController() {
        initializeSubscriptions();
    }

    private void initializeSubscriptions() {
        playTrackSubject
                .doOnNext(this::updateCurrentTrackAndDisposeSong)
                .map(this::createMediaPlayerForTrack)
                .subscribe(this::prepareMediaPlayer);

        repeatStatusSubject.subscribe(this::updateSongCycleCount);

    }

    private MediaPlayer createMediaPlayerForTrack(Track track) {
        return new MediaPlayer(new Media(track.getFilePath()));
    }

    private void updateCurrentTrackAndDisposeSong(Track track) {
        if (hasSong()) {
            disposeCurrentSong();
        }
        currentTrack = track;
    }

    private void prepareMediaPlayer(MediaPlayer mediaPlayer) {
        song = mediaPlayer;
        songStatusSubject.onNext(mediaPlayer.getStatus());
        attachSongListeners();
        song.play();
    }

    private void updateSongCycleCount(RepeatMode repeatMode) {
        if (hasSong()) {
            song.setCycleCount(repeatMode == RepeatMode.REPEAT_ONE ? MediaPlayer.INDEFINITE : 1);
        }
    }

    private void disposeCurrentSong() {
        if (hasSong()) {
            // Stop the song
            song.stop();

            // Unbind volume property
            song.volumeProperty().unbind();

            // Remove listeners
            song.setOnEndOfMedia(null);
            song.setOnReady(null);
            song.currentTimeProperty().removeListener(currentTimeListener);
            song.statusProperty().removeListener(statusListener);

            // Dispose of the media player
            song.dispose();

            // Clear references
            song = null;
            currentTrack = null;
        }
    }

    private void attachSongListeners() {
        endOfMediaListener = () -> {
            if (repeat == RepeatMode.REPEAT_ONE) {
                song.seek(Duration.ZERO);
                song.play();
            } else {
                playNextTrack();
            }
        };
        song.setOnEndOfMedia(endOfMediaListener);

        if (volume != null) {
            song.volumeProperty().bind(volume);
        }

        onReadyListener = () -> {
            SongStart songStart = new SongStart(song.getTotalDuration());
            songStartSubject.onNext(songStart);
        };

        song.setOnReady(onReadyListener);

        currentTimeListener = (observable, oldValue, newValue) -> {
            double currentTime = newValue.toSeconds();
            Progress progress = new Progress(currentTime);
            progressSubject.onNext(progress);
        };

        song.currentTimeProperty().addListener(currentTimeListener);

        statusListener = (observable, oldValue, newValue) -> {
            songStatusSubject.onNext(newValue);
        };
        song.statusProperty().addListener(statusListener);
    }

    // Public methods
    public static PlayerController getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void playPause() {
        if (hasSong()) {
            if (isPlaying()) {
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

        if (!tracks.isEmpty()) {
            clearQueue();
            playedTracks.clear();
            playTrackSubject.onNext(tracks.get(0));
            tracks.remove(0);
            addToQueue(tracks);
        }
    }

    public void play(Track track) {
        currentTrack = track;
        playTrackSubject.onNext(track);
    }

    public void playSingleTrack(Track track) {
        clearQueue();
        playedTracks.clear();
        play(track);
    }

    public void play(Queue queue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void resetSong() {
        if (hasSong()) {
            song.seek(song.getStartTime());
        }
    }

    public void toggleRepeat() {
        List<RepeatMode> repeatModes = new ArrayList<>(List.of(RepeatMode.values()));
        int index = repeatModes.indexOf(repeat);
        index = (index + 1) % repeatModes.size();
        repeat = repeatModes.get(index);
        repeatStatusSubject.onNext(repeat);
    }

    public void seek(double sTime) {
        if (hasSong()) {
            song.seek(Duration.seconds(sTime));
        }
    }

    public void bindVolume(DoubleProperty sliderValue) {
        volume = sliderValue;
        if (hasSong()) {
            song.volumeProperty().bind(sliderValue);
        }
    }

    public void setOnRepeat(Consumer<RepeatMode> action) {
        repeatStatusSubject.subscribe(action);
    }

    public void setOnProgress(Consumer<Progress> action) {
        progressSubject.subscribe(action);
    }

    public void setOnSongStart(Consumer<SongStart> action) {
        songStartSubject.subscribe(action);
    }

    public void setOnStatusChange(Consumer<MediaPlayer.Status> action) {
        songStatusSubject.subscribe(action);
    }

    public void setOnVolumeChange(Consumer<Double> action) {
        volumeChangedSubject.subscribe(action);
    }

    public void setOnEndOfMedia(Runnable action) {
        if (hasSong()) {
            song.setOnEndOfMedia(action);
        }
    }

    public void setOnPlaying(Runnable action) {
        if (hasSong()) {
            song.setOnPlaying(action);
        }
    }

    public void setOnPaused(Runnable action) {
        if (hasSong()) {
            song.setOnPaused(action);
        }
    }

    public DoubleProperty getVolumeProperty() {
        return volume;
    }

    public void setVolume(DoubleProperty volumeProperty) {
        volume = volumeProperty;
        if (hasSong()) {
            song.volumeProperty().bind(volumeProperty);
        }
    }

    public Deque<Track> getPlaybackQueue() {
        return playbackQueue;
    }

    public Deque<Track> getPlayedTracks() {
        return playedTracks;
    }

    public void stop() {
        if (hasSong()) {
            song.stop();
        }
    }

    public void playNextTrack() {
        boolean shouldPushToPlayedTracks = true;
        if (playbackQueue.isEmpty() && repeat == RepeatMode.REPEAT_ALL) {
            playbackQueue.addAll(playedTracks.reversed());
            playedTracks.clear();
            playbackQueue.offer(currentTrack);
            shouldPushToPlayedTracks = false;
        }

        if (playbackQueue.isEmpty()) {
            return;
        }

        if (currentTrack != null && shouldPushToPlayedTracks) {
            playedTracks.push(currentTrack);
        }

        disposeCurrentSong();
        Track nextTrack = playbackQueue.poll();
        if (nextTrack != null) {
            play(nextTrack);
        }
    }

    public void playPreviousTrack() {
        if (!playedTracks.isEmpty()) {
            Track previousTrack = playedTracks.pop();

            if (currentTrack != null && !currentTrack.equals(previousTrack)) {
                playbackQueue.offerFirst(currentTrack);
            }

            disposeCurrentSong();
            play(previousTrack);
        } else {
            resetSong();
        }
    }

    public boolean isPlaying() {
        return hasSong() && songStatusSubject.getValue() == MediaPlayer.Status.PLAYING;
    }

    public boolean hasSong() {
        return song != null;
    }

    public Track getTrack() {
        return currentTrack;
    }

    public void addToQueue(Track track) {
        playbackQueue.offer(track);
    }

    public void addToQueue(List<Track> tracks) {
        playbackQueue.addAll(tracks);
    }

    public void clearQueue() {
        playbackQueue.clear();
    }
}
