package hitbeat.view.playlists;

import hitbeat.controller.IndexController;
import hitbeat.controller.playlist.PlaylistsController;
import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlaylistView extends MFXScrollPane{
    private ObservableList<Playlist> playlists;
    private final PlaylistsController controller = new PlaylistsController();
    private final IndexController mioloController = IndexController.getInstance();

    public PlaylistView() {
        super();

        playlists = controller.fetchAll();

        HBox playlistHeader = new HBox();
        playlistHeader.setPrefHeight(50);

        MFXButton addPlaylistButton = new MFXButton();
        addPlaylistButton.setText("Add Playlist");
        addPlaylistButton.setOnAction(e -> {
            mioloController.loadPlaylistCreateView();
        });
        playlistHeader.getChildren().add(addPlaylistButton);

        ListView<Playlist> listView = new ListView<>(playlists, playlist -> {
            return new PlaylistCell(playlist);
        });

        listView.setItems(playlists);
        this.setContent(new VBox(playlistHeader, listView));

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }
}
