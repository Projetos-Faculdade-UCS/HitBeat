package hitbeat.view.playlists;

import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlaylistCell extends BaseCell<Playlist>{
    private Text playlistName = new Text("");
    private ImageView playlistCover = new ImageView();

    public PlaylistCell(Playlist playlist) {
        VBox playlistCard = new VBox();
        playlistCard.setPrefHeight(80);

        playlistCover.setFitWidth(150);
        playlistCover.setPreserveRatio(true);
        playlistName.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");
        playlistCard.getChildren().addAll( playlistCover, playlistName );

        updateItem(playlist);
        this.getChildren().add(playlistCard);
    }

    @Override
    public void updateItem(Playlist item) {
        if (item != null) {
            playlistName.setText(item.getName());
            playlistCover.setImage(item.getCover());
        }else{
            playlistName.setText("");
            playlistCover.setImage(null);
        }
        
    }
}
