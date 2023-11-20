package hitbeat.view.playlists;

import hitbeat.controller.IndexController;
import hitbeat.controller.player.PlayerController;
import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.Cover;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PlaylistCell extends BaseCell<Playlist> {
    private Text playlistName = new Text("");
    private Cover playlistCover = new Cover();
    private IndexController mioloController = IndexController.getInstance();

    public PlaylistCell(Playlist playlist) {
        VBox playlistCard = new VBox();
        playlistCard.setPrefHeight(80);

        playlistCover.setFit(150);

        playlistName.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        playlistName.setFill(Color.WHITE);
        playlistCard.getChildren().addAll(playlistCover, playlistName);

        updateItem(playlist);
        this.getChildren().add(playlistCard);

        // on hover, shows the play button
        this.setOnMouseEntered(e -> {
            playlistCover.showPlayButton(true);
        });

        // on exit, hides the play button
        this.setOnMouseExited(e -> {
            playlistCover.showPlayButton(false);
        });

    }

    @Override
    public void updateItem(Playlist item) {
        if (item != null) {
            playlistName.setText(item.getName());
            playlistCover.setCoverImage(item.getCover(150));
            this.setOnMouseClicked(e -> {
                if (!playlistCover.isMouseOverPlayButton())
                    mioloController.loadPlayListDetailView(item);
            });

            playlistCover.setPlayButtonAction(() -> {
                PlayerController.getInstance().play(item);
            });

        } else {
            playlistName.setText("");
            playlistCover.setCoverImage(null);
        }

    }
}
