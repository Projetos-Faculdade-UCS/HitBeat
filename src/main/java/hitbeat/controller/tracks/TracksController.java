package hitbeat.controller.tracks;

import hitbeat.controller.ModelController;
import hitbeat.dao.TrackDAO;
import hitbeat.model.Track;

public class TracksController extends ModelController<Track> {
    public TracksController() {
        super(new TrackDAO());
    }

    private TrackDAO getDao() {
        return (TrackDAO) dao;
    }

    public void toggleFavorite(Track track) {
        track.setFavorite(!track.isFavorite());
        getDao().save(track);
    }
}
