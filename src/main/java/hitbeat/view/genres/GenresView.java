package hitbeat.view.genres;

import hitbeat.controller.genres.GenresController;
import hitbeat.model.Genre;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;

public class GenresView extends MFXScrollPane {
    private ObservableList<Genre> genres;
    private final GenresController controller = new GenresController();

    public GenresView() {
        super();
        genres = controller.fetchAll();


        ListView<Genre> listView = new ListView<>(genres, genre -> {
            return new GenreCell(genre);
        });
        
        this.getStyleClass().add("transparent");

        

        
        this.setContent(listView);

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);

    }

    // class ListGenreCell extends ListCell<Genre> {
    //     private GenreCell genreCell;

    //     public ListGenreCell() {
    //         super();
    //         this.genreCell = new GenreCell(null);
    //     }

    //     @Override
    //     protected void updateItem(Genre genre, boolean empty) {
    //         super.updateItem(genre, empty);
    //         if (genre == null || empty) {
    //             genreCell.prefWidthProperty().unbind();
    //             setText(null);
    //             setGraphic(null);
    //             // hide the cell
    //             setId("hidden-list-cell");
    //             return;
    //         } else {
    //             genreCell.setGenre(genre);
    //             genreCell.prefWidthProperty().bind(Layout.getInstance().getContentWidth().subtract(20));

    //             Margin margin = new Margin(genreCell, 0, 0, 8, 0);

    //             setGraphic(margin);
    //             setOnMouseClicked(event -> {
    //                 System.out.println("Clicked on " + genre.getName());
    //             });
    //             genreCell.setId("list-cell");
    //         }
    //     }
    // }
}
