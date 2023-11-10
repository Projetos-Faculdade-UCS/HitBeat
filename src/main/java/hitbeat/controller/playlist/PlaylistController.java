package hitbeat.controller.playlist;

import java.util.ArrayList;
import java.util.List;

import hitbeat.dao.PlaylistDAO;
import hitbeat.model.Playlist;
import hitbeat.model.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlaylistController{
    private final PlaylistDAO dao;

    public PlaylistController(){
        dao = new PlaylistDAO();
    }

    public void createPlaylist(String name){
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(new Playlist(name));
        dao.bulkCreateOrUpdate(playlists, "name");
    }

    public ObservableList<Track> getAllTracks(Playlist playlist) {
        return FXCollections.observableArrayList(dao.getAllTracks(playlist));
    }
}
