package hitbeat.view.footer;

import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class PlayPauseBtn extends MFXButton {

    private boolean paused;
    private MediaPlayer mediaPlayer;
    SVGWidget svgPlay;
    SVGWidget svgPause;

    public PlayPauseBtn(MediaPlayer mediaPlayer) {
        super("");
        this.mediaPlayer = mediaPlayer;

        svgPlay = new SVGWidget("/hitbeat/svg/play.svg", 30, Color.WHITE);
        svgPause = new SVGWidget("/hitbeat/svg/pause.svg", 30, Color.WHITE);

        this.setOnAction(event -> {
            this.setPaused(!paused);
        });

        this.setPaused(true);
        this.setId("playPauseBtn");
    }

    private void setPaused(boolean paused) {
        this.paused = paused;
        if (!paused) {
            this.setGraphic(svgPause);
            mediaPlayer.play();
        } else {
            this.setGraphic(svgPlay);
            mediaPlayer.pause();
        }
    }
}