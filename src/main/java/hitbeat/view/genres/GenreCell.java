package hitbeat.view.genres;

import hitbeat.model.Genre;
import hitbeat.view.base.widgets.Widget;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class GenreCell extends Widget {
    private BorderPane root;
    private Genre genre;
    private Node center;

    public GenreCell(Genre genre) {
        this.genre = genre;
    }

    @Override
    public Node build() {
        root = new BorderPane();

        final String genreName = genre.getName();
        center = new GenreCellCenter(genreName).build();

        root.setCenter(center);

        root.setStyle("-fx-background-color: transparent;");

        return root;
    }
    
}
