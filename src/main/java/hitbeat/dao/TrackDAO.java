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

        Track track = new Track("Track 1", dataAtual, 100,
                "C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg",
                "C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3", false, false, false, genre);
        TrackDAO trackDAO = new TrackDAO();
        trackDAO.save(track);
    }

    @Override
    public void updateEntityProperties(Track existingTrack, Track newTrack) {
        existingTrack
                .withName(newTrack.getName())
                .withCreationDate(newTrack.getCreationDate())
                .withDuration(newTrack.getDuration())
                .withExplicit(newTrack.isExplicit())
                .withFavorite(newTrack.isFavorite())
                .withGenre(newTrack.getGenre())
                .withPicturePath(newTrack.getPicturePath())
                .withSingle(newTrack.isSingle());
    }
}
