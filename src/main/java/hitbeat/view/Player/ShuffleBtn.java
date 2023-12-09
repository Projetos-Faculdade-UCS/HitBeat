package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.controller.player.PlayerController.ShuffleMode;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;

public class ShuffleBtn extends MFXButton {
    public ShuffleBtn() {
        super("");
        this.getStyleClass().add("shuffle-btn");

        SVGWidget svgShuffle = new SVGWidget("/hitbeat/svg/shuffle.svg", 15);

        
        PlayerController player = PlayerController.getInstance();
        
        player.setOnShuffleChange(shuffle -> {
            setShuffle(shuffle == ShuffleMode.SHUFFLE);
        });
        
        this.setOnMouseClicked(event -> player.toggleShuffle());
        
        this.setShuffle(false);
        this.setGraphic(svgShuffle);
    }

    private void setShuffle(boolean shuffle) {
        if (shuffle) {
            this.addShuffle();
        } else {
            this.removeShuffle();
        }
    }

    private void addShuffle() {
        this.getStyleClass().remove("shuffle");
        this.getStyleClass().add("shuffle");
    }

    private void removeShuffle() {
        this.getStyleClass().remove("shuffle");
    }

}
