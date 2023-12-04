package hitbeat.controller.playlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hitbeat.controller.ModelController;
import hitbeat.dao.PlaylistDAO;
import hitbeat.dao.PlaylistTrackDAO;
import hitbeat.model.Playlist;
import hitbeat.model.PlaylistTrack;
import hitbeat.model.Track;
import hitbeat.util.HibernateUtil;
import hitbeat.util.Image2Bytes;
import jakarta.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

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

    public void createPlaylist(String name, String description, Image image){
        byte[] cover = null;
        if (image != null) {
            cover = Image2Bytes.convertImageToBytes(image);
        }
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist(name, description, cover));
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

    public Optional<Image> getImageFromDisk(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg"));
        return Optional.ofNullable(new Image(fileChooser.showOpenDialog(null).toURI().toString()));
        
    }
}
