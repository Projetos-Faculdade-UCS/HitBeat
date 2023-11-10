package hitbeat.controller.playlist;

import java.util.ArrayList;
import java.util.List;

import hitbeat.dao.PlaylistDAO;
import hitbeat.model.Playlist;

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
}
