package hitbeat.controller.genres;

import hitbeat.controller.ModelController;
import hitbeat.dao.GenreDAO;
import hitbeat.model.Genre;

public class GenresController extends ModelController<Genre> {
    public GenresController() {
        super(new GenreDAO());
    }
}
