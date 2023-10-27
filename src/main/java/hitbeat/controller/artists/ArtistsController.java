package hitbeat.controller.artists;

import hitbeat.controller.ModelController;
import hitbeat.dao.ArtistDAO;
import hitbeat.model.Artist;

public class ArtistsController extends ModelController<Artist> {
    public ArtistsController(){
        super(new ArtistDAO());
    }
}
