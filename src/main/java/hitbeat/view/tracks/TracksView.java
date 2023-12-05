package hitbeat.view.tracks;

import hitbeat.controller.tracks.TracksController;
import hitbeat.view.base.widgets.generic_list.GenericTrackList;

public class TracksView extends GenericTrackList {
    private final TracksController controller = new TracksController();

    public TracksView() {
        super();
        this.setTracksSupplier(controller::fetchAll);
    }

}
