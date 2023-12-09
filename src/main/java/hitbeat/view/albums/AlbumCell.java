package hitbeat.view.albums;

import hitbeat.controller.MioloController;
import hitbeat.controller.player.PlayerController;
import hitbeat.model.Album;
import hitbeat.view.base.widgets.Cover;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.control.Label;

public class AlbumCell extends BaseCell<Album> {
    private Album album;
    private Cover albumImage;
    private Label titleLabel;
    private Label subtitleLabel;

    public AlbumCell(Album album) {
        this.initUI();
        this.updateItem(album);
    }

    private void initUI() {
        albumImage = new Cover();
        albumImage.setFit(100);

        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");

        subtitleLabel = new Label();
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        ListTile listTile = new ListTile(albumImage, titleLabel, subtitleLabel, null);
        this.getChildren().add(listTile);

        // on hover, shows the play button
        this.setOnMouseEntered(e -> {
            albumImage.showPlayButton(true);
        });

        // on exit, hides the play button
        this.setOnMouseExited(e -> {
            albumImage.showPlayButton(false);
        });

        this.setOnMouseClicked(e -> {
            if (!albumImage.isMouseOverPlayButton()) {
                MioloController.getInstance().push(new AlbumDetailView(album), "albums", album.getName());
            }
        });
    }

    @Override
    public void updateItem(Album album) {
        this.album = album;

        if (album != null) {
            titleLabel.setText(this.album.getName());
            // subtitleLabel.setText(this.album.getDescription());
            albumImage.setCoverImage(this.album.getCover());
            albumImage.setPlayButtonAction(() -> {
                PlayerController.getInstance().play(this.album);
            });
        } else {
            titleLabel.setText("");
            subtitleLabel.setText("");
            albumImage.setCoverImage(null);
        }
    }

}
