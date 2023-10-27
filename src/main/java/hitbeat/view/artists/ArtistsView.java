package hitbeat.view.artists;

import hitbeat.controller.artists.ArtistsController;
import hitbeat.model.Artist;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;
public class ArtistsView extends MFXScrollPane {
    private ObservableList<Artist> artists;
    private final ArtistsController controller = new ArtistsController();

    public ArtistsView() {
        super();
        artists = controller.fetchAll();

        ListView<Artist> listView = new ListView<>(artists, artist -> {
            return new ArtistCell(artist);
        });
        this.setContent(listView);
    
        this.getStyleClass().add("transparent");
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }
}
