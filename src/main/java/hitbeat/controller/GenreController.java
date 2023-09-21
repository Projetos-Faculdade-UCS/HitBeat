package hitbeat.controller;


import java.util.List;
import java.util.stream.Collectors;

import hitbeat.dao.GenreDAO;
import hitbeat.model.Genre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class GenreController {

    @FXML
    private ListView<String> genreListView;

    private GenreDAO genreDAO = new GenreDAO();

    @FXML
    public void handleFetchGenresButtonAction(ActionEvent event) {
        List<Genre> genres = genreDAO.getGenresByName("example");
        List<String> genreNames = genres.stream().map(Genre::getName).collect(Collectors.toList());
        genreListView.getItems().setAll(genreNames);
    }
}

