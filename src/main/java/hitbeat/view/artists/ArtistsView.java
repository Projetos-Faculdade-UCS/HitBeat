package hitbeat.view.artists;

import java.util.Map;

import hitbeat.controller.artists.ArtistsController;
import hitbeat.model.Artist;
import hitbeat.util.AsyncLoading;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ArtistsView extends MFXScrollPane implements BaseView {
    private final ObservableList<Artist> artists = FXCollections.observableArrayList();
    private final ArtistsController controller = new ArtistsController();
    private final ListView<Artist> listView;

    public ArtistsView() {
        super();

        listView = new ListView<>(artists, artist -> {
            return new ArtistCell(artist);
        });
        this.setContent(listView);

        this.getStyleClass().add("transparent");
        this.setFitToWidth(true);
        this.setFitToHeight(true);

        this.loadData(controller::fetchAll);
    }

    @Override
    public Map<String, Object> getData() {
        return Map.of("artists", artists);
    }

    private void loadData(Supplier<ObservableList<Artist>> artistsSupplier) {
        Consumer<ObservableList<Artist>> consumer = artists -> {
            this.artists.setAll(artists);
            this.setContent(listView);
        };

        AsyncLoading.loadAsync(this, artistsSupplier, consumer);
    }
}
