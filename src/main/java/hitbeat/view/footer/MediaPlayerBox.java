package hitbeat.view.footer;

import hitbeat.styles.Styles;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class MediaPlayerBox extends VBox{

    public MediaPlayerBox(MediaPlayer mediaPlayer) {
        super(5);

        SVGWidget svgReset = new SVGWidget("/hitbeat/svg/reset.svg", 15, Color.WHITE);

        SVGWidget svgNext = new SVGWidget("/hitbeat/svg/next.svg", 15, Color.WHITE);

        SVGWidget svgPrevious = new SVGWidget("/hitbeat/svg/previous.svg", 15, Color.WHITE);

        PlayPauseBtn playPauseBtn = new PlayPauseBtn(mediaPlayer);
        RepeatBtn repeatBtn = new RepeatBtn(mediaPlayer);
                MFXButton resetBtn = new MFXButton("", svgReset.build());
        resetBtn.setOnAction(event -> {mediaPlayer.seek(mediaPlayer.getStartTime());});

        MFXButton nextBtn = new MFXButton("", svgNext.build());
        
        MFXButton previousBtn = new MFXButton("", svgPrevious.build());
        resetBtn.setStyle(Styles.GENERIC_BUTTON); 


        HBox actionsTab = new HBox(15);
        ProgressBarBox progressBar = new ProgressBarBox(mediaPlayer);

        actionsTab.getChildren().addAll(
            resetBtn, previousBtn, playPauseBtn, nextBtn, repeatBtn);
        actionsTab.getStyleClass().add("actions-tab");

        getChildren().addAll(actionsTab, progressBar);
    }


}
