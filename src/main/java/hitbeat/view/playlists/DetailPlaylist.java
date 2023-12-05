package hitbeat.view.playlists;

import java.util.Map;

import hitbeat.controller.playlist.PlaylistController;
import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.generic_list.GenericTrackList;

public class DetailPlaylist extends GenericTrackList {
    private final PlaylistController controller = new PlaylistController();
    private Playlist playlist;

    public DetailPlaylist(Playlist playlist) {
        super();
        this.playlist = playlist;
        this.setTracksSupplier(() -> controller.getAllTracks(playlist));
    }

    @Override
    public Map<String, Object> getData() {
        Map<String, Object> data = super.getData();

        data.put("playlist", playlist);

        return data;
    }

}
