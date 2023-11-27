package hitbeat.view.albums;

import hitbeat.model.Album;
import hitbeat.view.base.widgets.generic_list.GenericTrackList;

public class AlbumDetailView extends GenericTrackList {
    public AlbumDetailView(Album album) {
        super();

        this.setTracks(album.getTracks());
    }
}
