package hitbeat.view.tracks;

import hitbeat.controller.tracks.TracksController;
import hitbeat.model.Track;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;

public class TracksView extends MFXScrollPane implements BaseView{
    private ObservableList<Track> tracks;
    private final TracksController controller = new TracksController();

    public TracksView() {
        super(null);
        tracks = controller.fetchAll();

        ListView<Track> listView = new ListView<>(tracks, track -> {
            return new TrackCell(track);
        });

        listView.setItems(tracks);

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
