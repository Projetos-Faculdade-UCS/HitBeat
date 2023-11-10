package hitbeat.controller.playlist;

import hitbeat.controller.ModelController;
import hitbeat.dao.PlaylistDAO;
import hitbeat.model.Playlist;

public class PlaylistsController extends ModelController<Playlist>{
    public PlaylistsController(){
        super(new PlaylistDAO());
    }
}
