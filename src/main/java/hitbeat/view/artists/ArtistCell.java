package hitbeat.view.artists;

import hitbeat.controller.player.PlayerController;
import hitbeat.model.Artist;
import hitbeat.view.base.widgets.Cover;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.control.Label;

public class ArtistCell extends BaseCell<Artist> {
    private Artist artist;
    private Cover artistImage;
    private Label titleLabel;
    private Label subtitleLabel;

    public ArtistCell(Artist artist) {
        this.initUI();
        this.updateItem(artist);
    }

    private void initUI() {
        artistImage = new Cover();
        artistImage.setFit(60);

        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");

        subtitleLabel = new Label();
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        ListTile listTile = new ListTile(artistImage, titleLabel, subtitleLabel, null);
        this.getChildren().add(listTile);

        // on hover, shows the play button
        this.setOnMouseEntered(e -> {
            artistImage.showPlayButton(true);
        });

        // on exit, hides the play button
        this.setOnMouseExited(e -> {
            artistImage.showPlayButton(false);
        });
    }

    @Override
    public void updateItem(Artist artist) {
        this.artist = artist;

        if (artist != null) {
            titleLabel.setText(this.artist.getName());
            subtitleLabel.setText(this.artist.getDescription());
            artistImage.setCoverImage(this.artist.getCover());
            artistImage.setPlayButtonAction(() -> {
                PlayerController.getInstance().play(this.artist);
            });
        } else {
            titleLabel.setText("");
            subtitleLabel.setText("");
            artistImage.setCoverImage(null);
        }
    }

}
