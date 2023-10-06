package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.model.Track;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MusicBox extends HBox{
    Text trackName = new Text();
    Text artistName = new Text();
    
    ImageView imageView = new ImageView();

    public MusicBox() {
        super(15);
        this.setId("side-box");
        PlayerController player = PlayerController.getInstance();
        player.addOnReady(() -> {
            trackName.setText(player.getTrack().getName());
            artistName.setText(player.getTrack().getArtist().getName());
            imageView.setImage(this.getImage(player.getTrack()));
        });
        
        VBox trackInfo = new VBox(-1);
        trackInfo.setAlignment(Pos.CENTER_LEFT);
        trackInfo.getChildren().addAll(trackName, artistName);
        
        this.setStyles();
        this.getChildren().addAll(imageView, trackInfo);
    }

    private Image getImage(Track track) {
        if (track.getPicturePath() == null) {
            return new Image("/hitbeat/images/track.jpg");
        }
        return new Image(track.getPicturePath());
    }

    private void setStyles(){
        imageView.setPreserveRatio(true); //
        imageView.setSmooth(true);
        imageView.setFitHeight(60);
        
        Rectangle retangulo = new Rectangle(60, 60);
        retangulo.setArcWidth(10);
        retangulo.setArcHeight(10);
        imageView.setClip(retangulo);

        trackName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: #fff;");
        artistName.setStyle("-fx-font-size: 12px; -fx-fill: #fff;");
    }
}

