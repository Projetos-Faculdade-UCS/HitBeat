package hitbeat.dao;

import hitbeat.model.Track;

public class TrackDAO extends BaseDAO<Track> {

    public TrackDAO() {
        super(Track.class);
    }

    @Override
    public void updateProperties(Track existingTrack, Track newTrack) {
        existingTrack
                .withName(newTrack.getName())
                .withCreationDate(newTrack.getCreationDate())
                .withDuration(newTrack.getDuration())
                .withExplicit(newTrack.isExplicit())
                .withFavorite(newTrack.isFavorite())
                .withGenre(newTrack.getGenre())
                .withPicture(newTrack.getPicture())
                .withSingle(newTrack.isSingle());
    }
}
