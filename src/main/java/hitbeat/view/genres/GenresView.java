package hitbeat.view.genres;

import hitbeat.controller.genres.GenresController;
import hitbeat.controller.player.PlayerController;
import hitbeat.model.Genre;
import hitbeat.view.Layout;
import hitbeat.view.base.widgets.Margin;
import hitbeat.view.base.widgets.listview.ListView;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;

public class GenresView extends ListView<Genre> {
    private ObservableList<Genre> genres;
    private final GenresController controller = new GenresController();

    public GenresView() {
        super(null);
        genres = controller.fetchAll();
        this.setItems(genres);

        this.setCellFactory(genre -> {
            return new ListGenreCell();
        });
    }

    class ListGenreCell extends ListCell<Genre> {
        private GenreCell genreCell;

        public ListGenreCell() {
            super();
            this.genreCell = new GenreCell(null);
        }

        @Override
        protected void updateItem(Genre genre, boolean empty) {
            super.updateItem(genre, empty);
            if (genre == null || empty) {
                genreCell.prefWidthProperty().unbind();
                setText(null);
                setGraphic(null);
                // hide the cell
                setId("hidden-list-cell");
                return;
            } else {
                genreCell.setGenre(genre);
                genreCell.prefWidthProperty().bind(Layout.getInstance().getContentWidth().subtract(20));

                Margin margin = new Margin(genreCell, 0, 0, 8, 0);

                setGraphic(margin);
                setOnMouseClicked(event -> {
                    System.out.println("Clicked on " + genre.getName());
                    PlayerController.getInstance().playTeste();
                });
                genreCell.setId("list-cell");
            }
        }
    }
}
