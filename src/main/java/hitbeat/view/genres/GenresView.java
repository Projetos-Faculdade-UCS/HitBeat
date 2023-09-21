package hitbeat.view.genres;

import hitbeat.dao.GenreDAO;
import hitbeat.model.Genre;
import hitbeat.view.base.widgets.Widget;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class GenresView extends Widget {

    private VBox root;
    private ListView<Genre> genreListView;
    private ObservableList<Genre> genres;
    private final GenreDAO genreDAO = new GenreDAO();

    public GenresView() {
        initialize();
    }

    private void initialize() {
    }

    public Node getView() {
        return root;
    }

    @Override
    public Node build() {
        root = new VBox(10);
        genreListView = new ListView<>();
        genres = FXCollections.observableArrayList(genreDAO.getAllGenres());  // Fetch genres from DAO

        genreListView.setItems(genres);
        genreListView.setCellFactory(lv -> new javafx.scene.control.ListCell<Genre>() {
            @Override
            protected void updateItem(Genre genre, boolean empty) {
                super.updateItem(genre, empty);
                if (empty || genre == null) {
                    setText(null);
                } else {
                    setText(genre.getName());
                }
            }
        });

        root.getChildren().add(genreListView);
        return root;
    }
}
