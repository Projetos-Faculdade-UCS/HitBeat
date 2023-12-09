package hitbeat.view.favorites;

import hitbeat.controller.tracks.TracksController;
import hitbeat.view.base.widgets.generic_list.GenericTrackList;

public class FavoritesView extends GenericTrackList {
    private final TracksController controller = new TracksController();

    public FavoritesView() {
        super();
        this.setTracks(controller.getFavorites());
    }
}
