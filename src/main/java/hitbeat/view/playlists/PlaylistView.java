package hitbeat.view.playlists;

import hitbeat.controller.MioloController;
import hitbeat.controller.playlist.PlaylistController;
import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.FloatingActionButton;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class PlaylistView extends MFXScrollPane {
    private ObservableList<Playlist> playlists;
    private final PlaylistController controller = new PlaylistController();
    // private final IndexController mioloController = IndexController.getInstance();

    public PlaylistView() {
        super();
        MioloController.getInstance().setTitle("Playlists");

        playlists = controller.fetchAll();

        HBox playlistHeader = new HBox();
        playlistHeader.setPrefHeight(50);

        ListView<Playlist> listView = new ListView<>(playlists, playlist -> {
            return new PlaylistCell(playlist);
        });

        FloatingActionButton addPlaylistButton = new FloatingActionButton();
        Image add = new Image("/hitbeat/images/add-rounded.png", 30, 30, false, false);
        addPlaylistButton.setIcon(add);
        addPlaylistButton.setOnAction(e -> {
            MioloController.getInstance().loadPlaylistCreateView();
        });

        MioloController.getInstance().setFab(addPlaylistButton);
        
        this.setContent(listView);

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }
}
