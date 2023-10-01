package hitbeat.view.genres;

import hitbeat.controller.genres.GenresController;
import hitbeat.model.Genre;
import hitbeat.view.base.widgets.listview.ListView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GenresView extends ListView<Genre> {
    private ObservableList<Genre> genres;
    private final GenresController controller = new GenresController();

    public GenresView() {
        super(null);
        genres = controller.fetchAll();
        this.setItems(genres);

        this.setCellFactory(genre -> {
            return new ListCell<Genre>() {
                @Override
                protected void updateItem(Genre genre, boolean empty) {
                    super.updateItem(genre, empty);
                    if (genre == null || empty) {
                        setText(null);
                        setGraphic(null);
                        // hide the cell
                        setId("hidden-list-cell");
                        return;
                    } else {
                        VBox vbox = new VBox();
                        Node genreCell = new GenreCell(genre);
                        VBox.setVgrow(genreCell, Priority.ALWAYS);
                        HBox.setHgrow(genreCell, Priority.ALWAYS);
                        vbox.getChildren().add(genreCell);

                        HBox.setHgrow(vbox, Priority.ALWAYS);

                        setGraphic(vbox);
                        setOnMouseClicked(event -> {
                            System.out.println("Clicked on " + genre.getName());
                        });
                        setId("list-cell");
                    }
                }
            };
        });
    }
}
