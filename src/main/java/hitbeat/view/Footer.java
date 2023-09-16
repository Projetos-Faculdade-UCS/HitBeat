package hitbeat.view;

import hitbeat.styles.Styles;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Footer extends HBox{
    
    public Footer() {
        super(10);
        // ----------player de musica----------------
        String path2Song = getClass().getResource("/hitbeat/media/HitBeat.mp3").toString();
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(path2Song));

        //-----------------imagens------------------
        Image imagePlay = new Image(getClass().getResourceAsStream("/hitbeat/static/play-circle.png"));
        ImageView PlayView = new ImageView(imagePlay);

        Image imagePause = new Image(getClass().getResourceAsStream("/hitbeat/static/pause-circle.png"));
        ImageView PauseView = new ImageView(imagePause);

        Image imageReset = new Image(getClass().getResourceAsStream("/hitbeat/static/reset.png"));
        ImageView ResetView = new ImageView(imageReset);

        Image imagVolume = new Image(getClass().getResourceAsStream("/hitbeat/static/volume.png"));
        ImageView volumeView = new ImageView(imagVolume);

        //---------------botoes---------------------
        MFXButton playBtn = new MFXButton("", PlayView);
        playBtn.setOnAction(event -> {mediaPlayer.play();});

        MFXButton pauseBtn = new MFXButton("", PauseView);
        pauseBtn.setOnAction(event -> {mediaPlayer.pause();});

        MFXButton resetBtn = new MFXButton("", ResetView);
        resetBtn.setOnAction(event -> {mediaPlayer.seek(mediaPlayer.getStartTime());});
 
        Slider slider = new Slider(0, 1, 0.5);
        slider.setPrefWidth(150);
        mediaPlayer.volumeProperty().bind(slider.valueProperty());

        //---------------estilos & layout---------------------
        playBtn.setStyle(Styles.PLAYER_BUTTONS); 
        pauseBtn.setStyle(Styles.PLAYER_BUTTONS);
        resetBtn.setStyle(Styles.PLAYER_BUTTONS); 

        getChildren().addAll(playBtn, pauseBtn, resetBtn, volumeView, slider);

        setPrefHeight(70);
        setHgrow(this, Priority.ALWAYS);
        setStyle("-fx-alignment: center;");

    }

    
}
