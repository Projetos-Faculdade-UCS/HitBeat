package hitbeat.view.footer;

import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class MediaPlayerBox extends VBox {

    MediaPlayer mediaPlayer;

    public MediaPlayerBox(MediaPlayer mediaPlayer) {
        super(5);
        this.mediaPlayer = mediaPlayer;

        SVGWidget svgNext = new SVGWidget("/hitbeat/svg/next.svg", 15, Color.WHITE);
        SVGWidget svgPrevious = new SVGWidget("/hitbeat/svg/previous.svg", 15, Color.WHITE);

        PlayPauseBtn playPauseBtn = new PlayPauseBtn(mediaPlayer);
        RepeatBtn repeatBtn = new RepeatBtn(mediaPlayer);
        MFXButton resetBtn = getResetBtn();
        MFXButton nextBtn = new MFXButton("", svgNext);
        MFXButton previousBtn = new MFXButton("", svgPrevious);

        HBox actionsTab = new HBox(15);
        ProgressBar progressBar = new ProgressBar(mediaPlayer);

        actionsTab.getChildren().addAll(
                resetBtn, previousBtn, playPauseBtn, nextBtn, repeatBtn);

        actionsTab.setId("actionsTab");
        getChildren().addAll(actionsTab, progressBar);
    }

    public MFXButton getResetBtn() {
        SVGWidget svgReset = new SVGWidget("/hitbeat/svg/reset.svg", 15, Color.WHITE);
        MFXButton resetBtn = new MFXButton("", svgReset);
        resetBtn.setOnAction(event -> {
            this.mediaPlayer.seek(mediaPlayer.getStartTime());
        });

        return resetBtn;
    }

}
