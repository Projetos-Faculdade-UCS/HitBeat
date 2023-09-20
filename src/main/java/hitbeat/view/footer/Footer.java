package hitbeat.view.footer;

import hitbeat.styles.Styles;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class Footer extends HBox{
    
    public Footer() {
        super(10);
        // ----------player de musica----------------
        String path2Song = getClass().getResource("/hitbeat/media/HitBeat.mp3").toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(path2Song));

        //-----------------imagens------------------
        SVGWidget svgReset = new SVGWidget("/hitbeat/svg/reset.svg", 15, Color.WHITE);

        SVGWidget svgVolume = new SVGWidget("/hitbeat/svg/volume-max.svg", 15, Color.WHITE);

        //---------------botoes---------------------
        PlayPauseBtn playPauseBtn = new PlayPauseBtn(mediaPlayer);
        RepeatBtn repeatBtn = new RepeatBtn(mediaPlayer);

        MFXButton resetBtn = new MFXButton("", svgReset.build());
        resetBtn.setOnAction(event -> {mediaPlayer.seek(mediaPlayer.getStartTime());});


        //---------------sliders---------------------
        Slider volumeSlider = new Slider(0, 1, 0.5);
        volumeSlider.setPrefWidth(100);
        mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());

        ProgressBarDiv progressBar = new ProgressBarDiv(mediaPlayer);
        
        //---------------estilos & layout---------------------
        resetBtn.setStyle(Styles.PLAYER_BUTTONS); 

        getChildren().addAll(playPauseBtn, resetBtn, repeatBtn, progressBar, svgVolume.build(), volumeSlider);

        this.getStyleClass().add("footer");
    }

    
}