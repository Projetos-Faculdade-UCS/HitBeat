package hitbeat.view.tracks;

import hitbeat.controller.tracks.TracksController;
import hitbeat.model.Track;
import hitbeat.view.base.widgets.generic_list.GenericTrackList;
import javafx.collections.ObservableList;

public class TracksView extends GenericTrackList {
    private ObservableList<Track> tracks;
    private final TracksController controller = new TracksController();

    public TracksView() {
        super();
        tracks = controller.fetchAll();
        this.setTracks(tracks);
    }

    @Override
    public Object getData() {
        return null;
    }

}
