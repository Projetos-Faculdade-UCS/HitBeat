package hitbeat.view.playlists;

import hitbeat.controller.playlist.PlaylistController;
import hitbeat.model.Playlist;
import hitbeat.view.base.widgets.generic_list.GenericTrackList;

public class DetailPlaylist extends GenericTrackList {
    private final PlaylistController controller = new PlaylistController();
    private Playlist playlist;

    public DetailPlaylist(Playlist playlist) {
        super();
        this.playlist = playlist;
        this.setTracks(controller.getAllTracks(playlist));
    }

    @Override
    public Object getData() {
        return this.playlist;
    }

}
