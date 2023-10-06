package hitbeat.dao;

import java.util.Date;

import hitbeat.model.Genre;
import hitbeat.model.Track;

public class TrackDAO extends BaseDAO<Track> {

    public TrackDAO() {
        super(Track.class);
    }

    public static void mockData() {
        GenreDAO genreDAO = new GenreDAO();
        Genre genre = genreDAO.first();
        Date dataAtual = new Date();

        Track track = new Track();
        track.setName("Track 1");
        track.setCreationDate(dataAtual);
        track.setDuration(100);
        track.setExplicit(false);
        track.setFavorite(false);
        track.setGenre(genre);
        track.setArtist(null);
        TrackDAO trackDAO = new TrackDAO();
        trackDAO.save(track);
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
