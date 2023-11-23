package hitbeat.controller.playlist;

import java.util.ArrayList;
import java.util.List;

import hitbeat.controller.ModelController;
import hitbeat.dao.PlaylistDAO;
import hitbeat.dao.PlaylistTrackDAO;
import hitbeat.model.Playlist;
import hitbeat.model.PlaylistTrack;
import hitbeat.model.Track;
import hitbeat.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistController extends ModelController<Playlist>{
    public PlaylistController(){
        super(new PlaylistDAO());
    }

    private PlaylistDAO getDao() {
        return (PlaylistDAO) dao;
    }

    public ObservableList<Track> getAllTracks(Playlist playlist) {
        return FXCollections.observableArrayList(getDao().getAllTracks(playlist));
    }

    public void createPlaylist(String name){
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist(name));
        getDao().bulkCreateOrUpdate(playlists, "name");
    }

    public void addTrack(Playlist playlist, Track track) {
        List<PlaylistTrack> playlistTracks = new ArrayList<>();
        playlistTracks.add(new PlaylistTrack(playlist, track));

        PlaylistTrackDAO playlistTrackDAO = new PlaylistTrackDAO();
        playlistTrackDAO.bulkCreateOrUpdate(playlistTracks, "id");
    }

    public void removeTrack(Playlist playlist, Track track) {
        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();
        em.createNamedQuery("PlaylistTrack.delete")
                .setParameter("playlist", playlist)
                .setParameter("track", track)
                .executeUpdate();
        em.getTransaction().commit();

    }
}
