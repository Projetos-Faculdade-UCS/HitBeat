package hitbeat.view.footer;

import javafx.geometry.Pos;
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

        //---------------layout---------------------
        
        HBox spacer = new HBox();
        MediaPlayerBox mediaPlayerBox = new MediaPlayerBox(mediaPlayer);
        VolumeBox volumeBox = new VolumeBox(mediaPlayer);

        HBox.setHgrow(spacer, Priority.NEVER);
        HBox.setHgrow(mediaPlayerBox, Priority.ALWAYS);
        HBox.setHgrow(volumeBox, Priority.NEVER);

        volumeBox.setAlignment(Pos.CENTER_RIGHT);
        mediaPlayerBox.setAlignment(Pos.CENTER);
        spacer.setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(spacer, mediaPlayerBox,  volumeBox);

        //---------------estilos---------------------
        volumeBox.getStyleClass().add("side-box");
        spacer.getStyleClass().add("side-box");
        this.getStyleClass().add("footer");
        this.getStylesheets().add(getClass().getResource("/hitbeat/css/footer/footer.css").toExternalForm());
    }

    
}