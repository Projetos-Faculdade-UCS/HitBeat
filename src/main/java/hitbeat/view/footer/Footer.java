package hitbeat.view.footer;

import hitbeat.styles.Styles;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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

        SVGWidget svgNext = new SVGWidget("/hitbeat/svg/next.svg", 15, Color.WHITE);

        SVGWidget svgPrevious = new SVGWidget("/hitbeat/svg/previous.svg", 15, Color.WHITE);

        //---------------botoes---------------------
        PlayPauseBtn playPauseBtn = new PlayPauseBtn(mediaPlayer);
        RepeatBtn repeatBtn = new RepeatBtn(mediaPlayer);

        MFXButton resetBtn = new MFXButton("", svgReset.build());
        resetBtn.setOnAction(event -> {mediaPlayer.seek(mediaPlayer.getStartTime());});

        //---------------sliders---------------------
        ProgressBarDiv progressBar = new ProgressBarDiv(mediaPlayer);
        
        //---------------layout---------------------
        VolumeBox volumeBox = new VolumeBox(mediaPlayer);
        HBox spacer = new HBox();

        HBox actionsTab = new HBox(10);
        actionsTab.getChildren().addAll(
            resetBtn, svgPrevious.build(), playPauseBtn, svgNext.build(), repeatBtn);
        VBox mediaPlayerBox = new VBox(5);
        mediaPlayerBox.getChildren().addAll(actionsTab, progressBar);

        HBox.setHgrow(spacer, Priority.NEVER);
        HBox.setHgrow(mediaPlayerBox, Priority.ALWAYS);
        HBox.setHgrow(volumeBox, Priority.NEVER);

        volumeBox.setAlignment(Pos.CENTER_RIGHT);
        mediaPlayerBox.setAlignment(Pos.CENTER);
        spacer.setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(spacer, mediaPlayerBox,  volumeBox);

        //---------------estilos---------------------
        resetBtn.setStyle(Styles.PLAYER_BUTTONS); 
        actionsTab.getStyleClass().add("actions-tab");
        volumeBox.getStyleClass().add("side-box");
        spacer.getStyleClass().add("side-box");
        this.getStyleClass().add("footer");
    }

    
}