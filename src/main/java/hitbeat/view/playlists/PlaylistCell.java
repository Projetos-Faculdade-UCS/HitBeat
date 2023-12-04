package hitbeat.view.playlists;

import hitbeat.controller.Icons;
import hitbeat.controller.MioloController;
import hitbeat.controller.player.PlayerController;
import hitbeat.controller.playlist.PlaylistController;
import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.Cover;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.listview.BaseCell;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PlaylistCell extends BaseCell<Playlist> {
    private Playlist playlist;
    private PlaylistController controller = new PlaylistController();
    private Cover playlistImage;
    private Label titleLabel;
    private Label subtitleLabel;
    private BehaviorSubject<Playlist> onRemoveSubject = BehaviorSubject.create();


    public PlaylistCell(Playlist playlist) {
        this.initUI();
        this.updateItem(playlist);
    }

    private void initUI() {
        playlistImage = new Cover();
        playlistImage.setFit(100);

        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");

        subtitleLabel = new Label();
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        Button delBtn = new Button();
        delBtn.setGraphic( new Icons().getDelete());
        delBtn.setStyle("-fx-background-color: transparent;");
        delBtn.setOnAction(e -> {
            controller.delete(playlist);
            onRemoveSubject.onNext(this.playlist);
        });
        ListTile listTile = new ListTile(playlistImage, titleLabel, subtitleLabel, delBtn);
        this.getChildren().add(listTile);

        // on hover, shows the play button
        this.setOnMouseEntered(e -> {
            playlistImage.showPlayButton(true);
        });

        // on exit, hides the play button
        this.setOnMouseExited(e -> {
            playlistImage.showPlayButton(false);
        });

        this.setOnMouseClicked(e -> {
            if (!playlistImage.isMouseOverPlayButton()) {
                MioloController.getInstance().push(new DetailPlaylist(playlist), "playlists", playlist.getName());
            }
        });
    }

    public void setOnPlaylistRemoved(Consumer<Playlist> onRemove) {
        onRemoveSubject.subscribe(onRemove);
    }

    @Override
    public void updateItem(Playlist playlist) {
        this.playlist = playlist;

        if (playlist != null) {
            titleLabel.setText(this.playlist.getName());
            // subtitleLabel.setText(this.playlist.getDescription());
            playlistImage.setCoverImage(this.playlist.getCover(50));
            playlistImage.setPlayButtonAction(() -> {
                PlayerController.getInstance().play(this.playlist);
            });
            subtitleLabel.setText(this.playlist.getDescription());
        } else {
            titleLabel.setText("");
            subtitleLabel.setText("");
            playlistImage.setCoverImage(null);
        }
    }

}
