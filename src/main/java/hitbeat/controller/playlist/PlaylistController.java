package hitbeat.controller.playlist;

import java.util.ArrayList;
import java.util.List;

import hitbeat.controller.ModelController;
import hitbeat.dao.PlaylistDAO;
import hitbeat.dao.PlaylistTrackDAO;
import hitbeat.model.Playlist;
import hitbeat.model.PlaylistTrack;
import hitbeat.model.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistController extends ModelController<Playlist>{
    public PlaylistController(){
        super(new PlaylistDAO());
    }

    private PlaylistDAO getDao() {
        return (PlaylistDAO) dao;
    }

    public void createPlaylist(String name){
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist(name));
        getDao().bulkCreateOrUpdate(playlists, "name");
    }

    public ObservableList<Track> getAllTracks(Playlist playlist) {
        return FXCollections.observableArrayList(getDao().getAllTracks(playlist));
    }

    public void addTrack(Playlist playlist, Track track) {
        List<PlaylistTrack> playlistTracks = new ArrayList<>();
        playlistTracks.add(new PlaylistTrack(playlist, track));

        PlaylistTrackDAO playlistTrackDAO = new PlaylistTrackDAO();
        playlistTrackDAO.bulkCreateOrUpdate(playlistTracks, "id");
    }
}
