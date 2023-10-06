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
        MFXButton resetBtn = getResetBtn();
        MFXButton nextBtn = new MFXButton("", svgNext);
        MFXButton previousBtn = new MFXButton("", svgPrevious);

        HBox actionsTab = new HBox(15);
        ProgressBar progressBar = new ProgressBar();
        HBox.setHgrow(progressBar, Priority.ALWAYS);

        actionsTab.getChildren().addAll(
                resetBtn, previousBtn, playPauseBtn, nextBtn, repeatBtn);

        actionsTab.setId("actionsTab");
        HBox.setHgrow(actionsTab, Priority.NEVER);
        getChildren().addAll(actionsTab, progressBar);
        this.setStyle("-fx-min-width: 400px;");
    }

    public MFXButton getResetBtn() {
        SVGWidget svgReset = new SVGWidget("/hitbeat/svg/reset.svg", 15, Color.WHITE);
        MFXButton resetBtn = new MFXButton("", svgReset);

        resetBtn.setOnAction(event -> this.player.resetSong() );

        return resetBtn;
    }

}
