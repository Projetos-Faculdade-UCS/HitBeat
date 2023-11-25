package hitbeat.controller.genres;

import hitbeat.controller.ModelController;
import hitbeat.dao.GenreDAO;
import hitbeat.model.Genre;
import hitbeat.model.Track;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenresController extends ModelController<Genre> {
    public GenresController() {
        super(new GenreDAO());
    }

    public ObservableList<Track> getTracks(Genre genre) {
        return FXCollections.observableArrayList(genre.getTracks());
    }
}
