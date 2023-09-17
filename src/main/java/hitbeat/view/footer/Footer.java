package hitbeat.view.footer;

import hitbeat.styles.Styles;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;


public class Footer extends HBox{
    
    public Footer() {
        super(10);
        // ----------player de musica----------------
        String path2Song = getClass().getResource("/hitbeat/media/HitBeat.mp3").toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(path2Song));

        //-----------------imagens------------------
        SVGPath svgReset= new SVGPath();
        svgReset.setContent("M0 2a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V2z");
        svgReset.setFill(Color.WHITE);

        Image imageVolume = new Image(getClass().getResourceAsStream("/hitbeat/images/volume.png"));
        ImageView volumeView = new ImageView(imageVolume);

        //---------------botoes---------------------
        PlayPauseBtn playPauseBtn = new PlayPauseBtn(mediaPlayer);
        RepeatBtn repeatBtn = new RepeatBtn(mediaPlayer);

        MFXButton resetBtn = new MFXButton("", svgReset);
        resetBtn.setOnAction(event -> {mediaPlayer.seek(mediaPlayer.getStartTime());});


        //---------------sliders---------------------
        Slider volumeSlider = new Slider(0, 1, 0.5);
        volumeSlider.setPrefWidth(100);
        mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty());

        ProgressBarDiv progressBar = new ProgressBarDiv(mediaPlayer);
        
        //---------------estilos & layout---------------------
        resetBtn.setStyle(Styles.PLAYER_BUTTONS); 

        getChildren().addAll(playPauseBtn, resetBtn, repeatBtn, progressBar, volumeView, volumeSlider);

        this.getStyleClass().add("footer");
    }

    
}
