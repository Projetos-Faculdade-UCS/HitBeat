package hitbeat.view.genres;

import hitbeat.controller.genres.GenresController;
import hitbeat.model.Genre;
import hitbeat.view.base.widgets.Widget;
import hitbeat.view.base.widgets.listview.ListView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GenresView extends Widget {

    private Node root;
    private ObservableList<Genre> genres;
    private final GenresController controller = new GenresController();

    @Override
    public Node build() {

        genres = controller.fetchAllGenres();
        ListView<Genre> lv = new ListView<Genre>(genres);

        lv.setCellFactory(genre -> {
            return new ListCell<Genre>() {
        
                // Create components here if needed
        
                @Override
                protected void updateItem(Genre genre, boolean empty) {
                    super.updateItem(genre, empty);
                    if (genre == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {        
                        VBox vbox = new VBox();
                        Node genreCell = new GenreCell(genre);
                        VBox.setVgrow(genreCell, Priority.ALWAYS);
                        HBox.setHgrow(genreCell, Priority.ALWAYS);
                        vbox.getChildren().add(genreCell);
        
                        setGraphic(vbox);
                        setOnMouseClicked(event -> {
                            System.out.println("Clicked on " + genre.getName());
                        });
                    }
                }
            };
        });
        
        root = lv;

        return root;
    }
}
