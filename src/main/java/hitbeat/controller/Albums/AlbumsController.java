package hitbeat.controller.Albums;

import hitbeat.controller.ModelController;
import hitbeat.dao.AlbumDAO;
import hitbeat.model.Album;

public class AlbumsController extends ModelController<Album> {
    public AlbumsController() {
        super(new AlbumDAO());
    }
}
