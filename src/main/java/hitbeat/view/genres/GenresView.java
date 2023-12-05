package hitbeat.view.genres;

import java.util.Map;

import hitbeat.controller.genres.GenresController;
import hitbeat.model.Genre;
import hitbeat.util.AsyncLoading;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenresView extends MFXScrollPane implements BaseView {
    private final ObservableList<Genre> genres = FXCollections.observableArrayList();
    private final GenresController controller = new GenresController();
    private final ListView<Genre> listView;

    public GenresView() {
        super();

        listView = new ListView<>(genres, genre -> {
            return new GenreCell(genre);
        });

        this.getStyleClass().add("transparent");

        this.setContent(listView);

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);

        this.loadData(controller::fetchAll);
    }

    @Override
    public Map<String, Object> getData() {
        return null;
    }

    private void loadData(Supplier<ObservableList<Genre>> genresSupplier) {
        Consumer<ObservableList<Genre>> consumer = genres -> {
            this.genres.setAll(genres);
            this.setContent(listView);
        };

        AsyncLoading.loadAsync(this, genresSupplier, consumer);
    }
}
