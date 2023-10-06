package hitbeat.view.Player;

import hitbeat.view.base.widgets.Margin;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Footer extends HBox{
    
    public Footer() {
        super(10);
        //---------------layout---------------------
        MediaPlayerBox mediaPlayerBox = new MediaPlayerBox();
        Margin music = new Margin( new MusicBox(), 0, 0, 0, 20);
        Margin volume = new Margin( new VolumeBox(), 0, 0, 0, 20);
        
        HBox.setHgrow(music, Priority.NEVER);
        HBox.setHgrow(mediaPlayerBox, Priority.ALWAYS);
        HBox.setHgrow(volume, Priority.NEVER);

        volume.setAlignment(Pos.CENTER_RIGHT);
        mediaPlayerBox.setAlignment(Pos.CENTER);
        music.setAlignment(Pos.CENTER_LEFT);

        getChildren().addAll(music, mediaPlayerBox, volume);

        //---------------estilos---------------------
        this.setId("footer");
        this.getStylesheets().add(
            getClass().getResource("/hitbeat/css/footer/footer.css").toExternalForm());
    }

    
}