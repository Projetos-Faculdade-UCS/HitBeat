package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;

public class PlayPauseBtn extends MFXButton {

    private PlayerController player;
    SVGWidget svgPlay;
    SVGWidget svgPause;

    public PlayPauseBtn() {
        super("");

        this.player = PlayerController.getInstance();

        svgPlay = new SVGWidget("/hitbeat/svg/play-circle.svg", 30, Color.WHITE);
        svgPause = new SVGWidget("/hitbeat/svg/pause.svg", 30, Color.WHITE);

        this.setOnAction(event -> player.playPause());
        player.setOnStatusChange((status) -> {
            if (status == Status.PLAYING) {
                this.setGraphic(svgPause);
            } else {
                this.setGraphic(svgPlay);
            }
        });

        this.setGraphic(svgPlay);
        this.setId("playPauseBtn");
    }

}