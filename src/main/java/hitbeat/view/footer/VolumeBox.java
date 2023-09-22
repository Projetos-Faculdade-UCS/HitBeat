package hitbeat.view.footer;

import hitbeat.styles.Styles;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class VolumeBox extends HBox{

        boolean muted = false;
        double volume = 0.5;
        
        public VolumeBox(MediaPlayer mediaPlayer) {
            super(0);

            Slider volumeSlider = new Slider(0, 1, volume);
            volumeSlider.setPrefWidth(100);
            mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());

            SVGWidget svgMute = new SVGWidget("/hitbeat/svg/volume-mute.svg", 15, Color.WHITE);
            SVGWidget svgVolume = new SVGWidget("/hitbeat/svg/volume-max.svg", 15, Color.WHITE);
            
            MFXButton muteBtn = new MFXButton("", svgVolume.build());

            muteBtn.setOnAction(e -> {
                if (muted) {
                    muted = false;
                    volumeSlider.setValue(volume);
                } else {
                    muted = true;
                    volume = volumeSlider.getValue();
                    volumeSlider.setValue(0);
                }
            });

            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.doubleValue() == 0) {
                    muteBtn.setGraphic(svgMute.build());
                } else {
                    muteBtn.setGraphic(svgVolume.build());
                }
            });

            muteBtn.setStyle(Styles.GENERIC_BUTTON);
            this.getChildren().addAll(muteBtn, volumeSlider);
        }
}
