package hitbeat.view.footer;

import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class PlayPauseBtn extends MFXButton{
    
    private boolean paused = true;

    public PlayPauseBtn(MediaPlayer mediaPlayer) {
        super("");

        SVGWidget svgPlay = new SVGWidget("/hitbeat/svg/play.svg", 30, Color.WHITE);
        SVGWidget svgPause = new SVGWidget("/hitbeat/svg/pause.svg", 30, Color.WHITE);

        this.setGraphic(svgPlay.build());
        this.setOnAction(event -> {
            if (paused == true) {
                paused = false;
                this.setGraphic(svgPause.build());
                mediaPlayer.play();
            } else {
                paused = true;
                this.setGraphic(svgPlay.build());
                mediaPlayer.pause();
            }
        });
        this.setId("playPauseBtn");

        this.getStylesheets().add(getClass().getResource("/hitbeat/css/footer/footer.css").toExternalForm());
    }
}