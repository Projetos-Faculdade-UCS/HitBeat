package hitbeat.view.footer;

import hitbeat.view.base.widgets.SVGWidget;
import hitbeat.view.base.wrappers.Slider;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class VolumeBox extends HBox{

    boolean muted = false;
    double volume = 0.5;
    
    public VolumeBox(MediaPlayer mediaPlayer) {
        super(15);

        Slider volumeSlider = new Slider(0, 1, volume);
        volumeSlider.setDecimalPrecision(2);
        volumeSlider.getStyleClass().add("volume-slider");
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

        this.setId("side-box");
        this.getChildren().addAll(muteBtn, volumeSlider);
    }
}
