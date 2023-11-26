package hitbeat.view.albums;

import hitbeat.controller.Albums.AlbumsController;
import hitbeat.model.Album;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;

public class AlbumsView extends MFXScrollPane implements BaseView {
    private ObservableList<Album> albums;
    private final AlbumsController controller = new AlbumsController();

    public AlbumsView() {
        super();
        albums = controller.fetchAll();

        ListView<Album> listView = new ListView<>(albums, album -> {
            return new AlbumCell(album);
        });
        this.setContent(listView);

        this.getStyleClass().add("transparent");
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    @Override
    public Object getData() {
        return albums;
    }
}