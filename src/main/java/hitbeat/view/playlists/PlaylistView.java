package hitbeat.view.playlists;

import java.util.HashMap;
import java.util.Map;

import hitbeat.controller.MioloController;
import hitbeat.controller.playlist.PlaylistController;
import hitbeat.model.Playlist;
import hitbeat.util.AsyncLoading;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.FloatingActionButton;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class PlaylistView extends MFXScrollPane implements BaseView {
    private ObservableList<Playlist> playlists = FXCollections.observableArrayList();
    private final PlaylistController controller = new PlaylistController();
    private final ListView<Playlist> listView;

    public PlaylistView() {
        super();
        MioloController.getInstance().setTitle("Playlists");

        HBox playlistHeader = new HBox();
        playlistHeader.setPrefHeight(50);

        listView = new ListView<>(playlists, playlist -> {
            PlaylistCell playlistCell = new PlaylistCell(playlist);
            playlistCell.setOnPlaylistRemoved(playlistRm -> {
                playlists.remove(playlistRm);
            });
            return playlistCell;
        });

        this.setContent(listView);

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);

        this.loadData(controller::fetchAll);
    }

    public static FloatingActionButton getFab() {
        FloatingActionButton addPlaylistButton = new FloatingActionButton();
        Image add = new Image("/hitbeat/images/add-rounded.png", 30, 30, false, false);
        addPlaylistButton.setIcon(add);
        addPlaylistButton.setOnAction(e -> {
            CreatePlaylist createPlaylist = new CreatePlaylist();
            MioloController.getInstance().push(createPlaylist, "addPlaylist", "Adicionar Playlist", createPlaylist.getFab());
        });

        return addPlaylistButton;
    }

    @Override
    public Map<String, Object> getData() {
        return new HashMap<String, Object>() {{
            put("playlists", playlists);
        }};
    }

    private void loadData(Supplier<ObservableList<Playlist>> playlistsSupplier) {
        Consumer<ObservableList<Playlist>> consumer = playlists -> {
            this.playlists.setAll(playlists);
            this.setContent(listView);
        };

        AsyncLoading.loadAsync(this, playlistsSupplier, consumer);
    }
}
