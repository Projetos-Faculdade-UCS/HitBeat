package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.controller.player.PlayerController.SongStart;
import javafx.geometry.Pos;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class MusicBox extends HBox {
    Text trackName = new Text();
    Text artistName = new Text();
    Tooltip trackTooltip = new Tooltip();
    Tooltip artistTooltip = new Tooltip();

    VBox infoBox = new VBox(1);
    ImageView imageView = new ImageView();

    public MusicBox() {
        super(10);
        this.getStyleClass().addAll("side-box", "playing-track");

        PlayerController player = PlayerController.getInstance();
        player.setOnSongStart(this::updateTrackInfo);

        // Configura Tooltips para os Labels
        Tooltip.install(trackName, trackTooltip);
        Tooltip.install(artistName, artistTooltip);

        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(trackName, artistName);

        HBox.setHgrow(infoBox, Priority.ALWAYS);

        this.setStyles();
        this.getChildren().addAll(imageView, infoBox);
    }

    private void updateTrackInfo(SongStart song) {
        if (song.getTrack() != null) {
            String vTrackName = song.getTrack().getName();
            String vArtistName = song.getTrack().getAlbum().getArtist().getName();
            imageView.setImage(song.getTrack().getCover());

            trackName.setText(vTrackName);
            trackTooltip.setText(vTrackName);

            artistName.setText(vArtistName);
            artistTooltip.setText(vArtistName);
        }
    }

    private void setStyles() {
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setFitWidth(60);

        Rectangle retangulo = new Rectangle(60, 60);
        retangulo.setArcWidth(10);
        retangulo.setArcHeight(10);
        imageView.setClip(retangulo);

        trackName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: #fff;");
        artistName.setStyle("-fx-font-size: 12px; -fx-fill: #A9A9A9;");

        infoBox.setStyle("-fx-min-width: 105px; -fx-max-width: 600px;");

        Rectangle infoMask = new Rectangle(105, 100);
        infoMask.widthProperty().bind(infoBox.widthProperty());
        infoBox.setClip(infoMask);
    }
}
