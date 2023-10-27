package hitbeat.view.artists;

import hitbeat.model.Artist;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ArtistCell extends BaseCell<Artist> {
    private Artist artist;
    private ImageView artistImage;
    private Label titleLabel;
    private Label subtitleLabel;
    
    public ArtistCell(Artist artist) {
        this.initUI();
        this.updateItem(artist);
    }

    private void initUI() {
        artistImage = new ImageView();
        artistImage.setPreserveRatio(true); //
        artistImage.setSmooth(true);
        artistImage.setFitWidth(60);

        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");

        subtitleLabel = new Label();
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        ListTile listTile = new ListTile(artistImage, titleLabel, subtitleLabel, null);
        this.getChildren().add(listTile);
    }

    @Override
    public void updateItem(Artist artist) {
        this.artist = artist;

        if (artist != null) {
            titleLabel.setText(this.artist.getName());
            subtitleLabel.setText(this.artist.getDescription());
            artistImage.setImage( this.artist.getCover() );
        } else {
            titleLabel.setText("");
            subtitleLabel.setText("");
            artistImage.setImage(null);
        }
    }
    
}
