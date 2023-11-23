package hitbeat.dao;

import java.util.List;

import hitbeat.model.Artist;
import hitbeat.model.Track;
import hitbeat.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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
                .withSingle(newTrack.isSingle());
    }

    public List<Track> findByAlbumArtist(Artist artist) {
        EntityManager em = HibernateUtil.getEntityManager();
        TypedQuery<Track> query = em.createNamedQuery("Track.findByAlbumArtist", Track.class);
        query.setParameter("artist", artist);
        return query.getResultList();
    }

    public List<Track> getFavorites() {
        EntityManager em = HibernateUtil.getEntityManager();
        TypedQuery<Track> query = em.createNamedQuery("Track.getFavorites", Track.class);
        return query.getResultList();
    }
}
