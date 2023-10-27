package hitbeat.view.playlists;

import hitbeat.controller.playlists.PlaylistsController;
import hitbeat.model.Playlist;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;

public class PlaylistsView extends MFXScrollPane{
    private ObservableList<Playlist> playlists;
    private final PlaylistsController controller = new PlaylistsController();

    public PlaylistsView() {
        super();
    }
}
