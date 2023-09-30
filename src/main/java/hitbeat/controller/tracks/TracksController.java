package hitbeat.controller.tracks;

import hitbeat.controller.ModelController;
import hitbeat.dao.TrackDAO;
import hitbeat.model.Track;

public class TracksController extends ModelController<Track> {
    public TracksController() {
        super(new TrackDAO());
    }
}
