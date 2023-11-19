package hitbeat.view.playlists;

import hitbeat.controller.IndexController;
import hitbeat.controller.player.PlayerController;
import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.listview.BaseCell;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PlaylistCell extends BaseCell<Playlist>{
    private Text playlistName = new Text("");
    private ImageView playlistCover = new ImageView();
    private IndexController mioloController = IndexController.getInstance();
    private MFXButton playButton;

    public PlaylistCell(Playlist playlist) {
        VBox playlistCard = new VBox();
        playlistCard.setPrefHeight(80);

        playlistCover.setFitWidth(150);
        playlistCover.setPreserveRatio(true);
        playlistName.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        playlistName.setFill(Color.WHITE);
        playlistCard.getChildren().addAll( playlistCover, playlistName );

        playButton = new MFXButton("Play");
        playButton.setMinWidth(150);
        playButton.setMinHeight(30);
        playButton.setTranslateY(-10);
        playButton.setTranslateX(0);

        playlistCard.getChildren().add(playButton);


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

            playButton.setOnAction(e -> {
                PlayerController.getInstance().play(item);
            });
        }else{
            playlistName.setText("");
            playlistCover.setImage(null);
        }
        
    }
}
