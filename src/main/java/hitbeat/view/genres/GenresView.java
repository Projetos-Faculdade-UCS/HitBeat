package hitbeat.view.genres;

import java.util.Map;

import hitbeat.controller.genres.GenresController;
import hitbeat.model.Genre;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;

public class GenresView extends MFXScrollPane implements BaseView {
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

    @Override
    public Map<String, Object> getData() {
        return null;
    }
}
