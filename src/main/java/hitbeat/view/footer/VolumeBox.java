package hitbeat.view.footer;

import hitbeat.view.base.widgets.SVGWidget;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class VolumeBox extends HBox{
        
        public VolumeBox(MediaPlayer mediaPlayer) {
            super(5);

            Slider volumeSlider = new Slider(0, 1, 0.5);
            volumeSlider.setPrefWidth(100);
            mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());

            SVGWidget svgVolume = new SVGWidget("/hitbeat/svg/volume-max.svg", 15, Color.WHITE);

            this.getChildren().addAll(svgVolume.build(), volumeSlider);
        }
}
