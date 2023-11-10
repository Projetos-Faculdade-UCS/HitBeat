package hitbeat.view.playlists;

import hitbeat.controller.IndexController;
import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PlaylistCell extends BaseCell<Playlist>{
    private Text playlistName = new Text("");
    private ImageView playlistCover = new ImageView();
    private IndexController mioloController = IndexController.getInstance();

    public PlaylistCell(Playlist playlist) {
        VBox playlistCard = new VBox();
        playlistCard.setPrefHeight(80);

        playlistCover.setFitWidth(150);
        playlistCover.setPreserveRatio(true);
        playlistName.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        playlistName.setFill(Color.WHITE);
        playlistCard.getChildren().addAll( playlistCover, playlistName );

        updateItem(playlist);
        this.getChildren().add(playlistCard);
    }

    @Override
    public void updateItem(Playlist item) {
        if (item != null) {
            playlistName.setText(item.getName());
            playlistCover.setImage(item.getCover());
            this.setOnMouseClicked(e -> {
                mioloController.loadPlayListDetailView(item);
            });
        }else{
            playlistName.setText("");
            playlistCover.setImage(null);
        }
        
    }
}
