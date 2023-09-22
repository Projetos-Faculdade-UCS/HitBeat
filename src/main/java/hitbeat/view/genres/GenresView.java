package hitbeat.view.genres;

import java.util.function.Function;

import hitbeat.controller.genres.GenresController;
import hitbeat.model.Genre;
import hitbeat.view.base.widgets.Widget;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class GenresView extends Widget {

    private ListView<Genre> root;
    @FXML
    private ObservableList<Genre> genres;
    private final GenresController controller = new GenresController();

    @Override
    public Node build() {
        
        genres = controller.fetchAllGenres();
        root = new ListView<Genre>(genres);

        Function<Genre, MFXListCell<Genre>> cellFactory = genre -> {
            return new GenreCellFactory(root.getListView(), genre);
        };

        root.setCellFactory(cellFactory);
        
        return root.build();
    }

    private static class GenreCellFactory extends MFXListCell<Genre> {
        public GenreCellFactory(MFXListView<Genre> listView, Genre data) {
            super(listView, data);
        }

        @Override
        protected void render(Genre genre) {
            if (genre == null) {
                return;
            }
            
            this.setOnMouseClicked(event -> {
                System.out.println("GenreCell: " + genre.getName() + " clicked");
            });
            
            GenreCell genreCell = new GenreCell(genre);
            this.getChildren().add(genreCell.build());
        }
    }
}
