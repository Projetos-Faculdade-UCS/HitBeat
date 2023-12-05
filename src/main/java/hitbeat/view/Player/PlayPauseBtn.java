package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;

public class PlayPauseBtn extends MFXButton {

    private PlayerController player;
    private SVGWidget svgPlay;
    private SVGWidget svgPause;
    private ImageView fire;

    public PlayPauseBtn() {
        super("");

        this.player = PlayerController.getInstance();

        svgPlay = new SVGWidget("/hitbeat/svg/play-circle.svg", 30, Color.WHITE);
        svgPause = new SVGWidget("/hitbeat/svg/pause.svg", 30, Color.WHITE);

        Image fireGif = new Image("/hitbeat/gifs/fire-daora2.gif");
        fire = new ImageView(fireGif);

        fire.setOpacity(0);

        fire.setFitHeight(40);
        fire.setFitWidth(40);

        StackPane playPauseBtn = new StackPane();
        playPauseBtn.getChildren().addAll(svgPlay, fire);

        this.setOnAction(event -> player.playPause());
        player.setOnStatusChange((status) -> {
            if (status == Status.PLAYING) {
                showFire();
                playPauseBtn.getChildren().clear();
                playPauseBtn.getChildren().addAll(svgPause, fire);
            } else {
                hideFire();
                playPauseBtn.getChildren().clear();
                playPauseBtn.getChildren().addAll(svgPlay, fire);
            }
        });

        this.setGraphic(playPauseBtn);
        this.setId("playPauseBtn");
    }

    private void showFire() {
        fire.setOpacity(0.6);
    }

    private void hideFire() {
        fire.setOpacity(0.0);
    }

}