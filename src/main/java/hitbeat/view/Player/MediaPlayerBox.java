package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MediaPlayerBox extends VBox {

    PlayerController player;

    public MediaPlayerBox() {
        super(5);
        this.player = PlayerController.getInstance();

        SVGWidget svgNext = new SVGWidget("/hitbeat/svg/next.svg", 15, Color.WHITE);
        SVGWidget svgPrevious = new SVGWidget("/hitbeat/svg/previous.svg", 15, Color.WHITE);

        PlayPauseBtn playPauseBtn = new PlayPauseBtn();
        RepeatBtn repeatBtn = new RepeatBtn();
        ShuffleBtn shuffleBtn = new ShuffleBtn();
        MFXButton stopBtn = getStopBtn();
        MFXButton nextBtn = new MFXButton("", svgNext);

        nextBtn.setOnMouseClicked(event -> this.player.playNextTrack() );

        MFXButton previousBtn = new MFXButton("", svgPrevious);

        // previous: double click to previous track
        previousBtn.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                this.player.playPreviousTrack();
            }
            if (event.getClickCount() == 1) {
                this.player.resetSong();
            }
        });

        HBox actionsTab = new HBox(15);
        ProgressBar progressBar = new ProgressBar();
        HBox.setHgrow(progressBar, Priority.ALWAYS);

        actionsTab.getChildren().addAll(
                stopBtn, shuffleBtn, previousBtn, playPauseBtn, nextBtn, repeatBtn);

        actionsTab.setId("actionsTab");
        HBox.setHgrow(actionsTab, Priority.NEVER);
        getChildren().addAll(actionsTab, progressBar);
        this.setStyle("-fx-min-width: 400px;");
    }

    public MFXButton getStopBtn() {
        SVGWidget svgStop = new SVGWidget("/hitbeat/svg/stop.svg", 15, Color.WHITE);
        MFXButton stopBtn = new MFXButton("", svgStop);

        stopBtn.setOnAction(event -> this.player.stop() );

        return stopBtn;
    }

}
