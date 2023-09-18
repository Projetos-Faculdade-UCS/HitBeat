package hitbeat.view.footer;

import hitbeat.styles.Styles;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class Footer extends HBox{
    
    public Footer() {
        super(10);
        // ----------player de musica----------------
        String path2Song = getClass().getResource("/hitbeat/media/HitBeat.mp3").toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(path2Song));

        //-----------------imagens------------------
        Image imageReset = new Image(getClass().getResourceAsStream("/hitbeat/images/reset.png"));
        ImageView ResetView = new ImageView(imageReset);

        Image imageVolume = new Image(getClass().getResourceAsStream("/hitbeat/images/volume.png"));
        ImageView volumeView = new ImageView(imageVolume);

        //---------------botoes---------------------
        PlayPauseBtn playPauseBtn = new PlayPauseBtn(mediaPlayer);
        RepeatBtn repeatBtn = new RepeatBtn(mediaPlayer);

        MFXButton resetBtn = new MFXButton("", ResetView);
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
