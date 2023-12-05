package hitbeat.view.albums;

import java.util.Map;

import hitbeat.controller.Albums.AlbumsController;
import hitbeat.model.Album;
import hitbeat.util.AsyncLoading;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlbumsView extends MFXScrollPane implements BaseView {
    private final ObservableList<Album> albums = FXCollections.observableArrayList();
    private final AlbumsController controller = new AlbumsController();
    private final ListView<Album> listView;

    public AlbumsView() {
        super();

        listView = new ListView<>(albums, album -> {
            return new AlbumCell(album);
        });
        this.setContent(listView);

        this.getStyleClass().add("transparent");
        this.setFitToWidth(true);
        this.setFitToHeight(true);

        this.loadData(controller::fetchAll);
    }

    @Override
    public Map<String, Object> getData() {
        return Map.of("albums", albums);
    }

    private void loadData(Supplier<ObservableList<Album>> albumsSupplier) {
        Consumer<ObservableList<Album>> consumer = albums -> {
            this.albums.setAll(albums);
            this.setContent(listView);
        };

        AsyncLoading.loadAsync(this, albumsSupplier, consumer);
    }
}