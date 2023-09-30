package hitbeat.controller.genres;

import hitbeat.dao.GenreDAO;
import hitbeat.model.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenresController {
    
    private final GenreDAO genreDAO = new GenreDAO();

    public ObservableList<Genre> fetchAllGenres() {
        return FXCollections.observableArrayList(genreDAO.getAll());
    }

    // Other potential methods related to genres (e.g., save, delete) can be added here.
}

