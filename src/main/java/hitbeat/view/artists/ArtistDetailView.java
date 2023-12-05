package hitbeat.view.artists;

import hitbeat.model.Artist;
import hitbeat.view.base.widgets.generic_list.GenericTrackList;

public class ArtistDetailView extends GenericTrackList {

    public ArtistDetailView(Artist artist) {
        super();

        this.setTracksSupplier(() -> artist.getTracks());
    }

}
