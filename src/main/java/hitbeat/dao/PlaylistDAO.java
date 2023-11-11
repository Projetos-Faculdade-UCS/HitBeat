package hitbeat.dao;

import java.util.List;

import hitbeat.model.Playlist;
import hitbeat.model.Track;

public class PlaylistDAO extends BaseDAO<Playlist>{
    public PlaylistDAO(){
        super(Playlist.class);
    }
    
    @Override
    protected void updateProperties(Playlist existingEntity, Playlist newEntity) {
        existingEntity.setName(newEntity.getName());
        existingEntity.setDescription(newEntity.getDescription());
        existingEntity.setFilePath(newEntity.getFilePath());
    }

    public List<Track> getAllTracks(Playlist playlist) {
        return this.executeMethod(session -> {
            String hql = "SELECT pt.track FROM PlaylistTrack pt WHERE pt.playlist = :playlist";
            return session.createQuery(hql, Track.class)
                    .setParameter("playlist", playlist)
                    .list();
        });
    }

    // public void addTrack(Playlist playlist, Track track) {
    //     this.executeMethod(session -> {
    //         String hql = "INSERT INTO PlaylistTrack (playlist, track) VALUES (:playlist, :track)";
    //         session.createQuery(hql, null)
    //                 .setParameter("playlist", playlist)
    //                 .setParameter("track", track)
    //                 .executeUpdate();
    //         return null;
    //     });
    // }
}
