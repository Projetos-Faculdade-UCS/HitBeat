package hitbeat.view.favorites;

import hitbeat.controller.MioloController;
import hitbeat.controller.tracks.TracksController;
import hitbeat.model.Track;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import hitbeat.view.tracks.TrackCell;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

public class FavoritesView extends MFXScrollPane implements BaseView{
    private ObservableList<Track> tracks;
    private final TracksController controller = new TracksController();

    public FavoritesView() {
        super();
        MioloController.getInstance().setTitle("Músicas favoritas");

        tracks = controller.getFavorites();

        HBox favoritesHeader = new HBox();
        favoritesHeader.setPrefHeight(50);

        ListView<Track> listView = new ListView<>(tracks, track -> {
            return new TrackCell(track);
        });
        
        this.setContent(listView);

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    @Override
    public Object getData() {
        return null;
    }
}
