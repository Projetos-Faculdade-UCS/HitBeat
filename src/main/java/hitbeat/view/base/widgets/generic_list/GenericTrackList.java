package hitbeat.view.base.widgets.generic_list;

import hitbeat.model.Track;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import hitbeat.view.tracks.TrackCell;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenericTrackList extends MFXScrollPane implements BaseView {
    private ObservableList<Track> tracks = FXCollections.observableArrayList();
    private final ListView<Track> listView;

    public GenericTrackList() {
        super(null);

        TrackCell trackCell = new TrackCell(null);
        trackCell.setOnTracksChange(tracks -> {
            this.tracks.setAll(tracks);
        });

        listView = new ListView<>(tracks, track -> {
            return trackCell;
        });

        this.setContent(listView);

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    @Override
    public Object getData() {
        return this.tracks;
    }

    public void setTracks(ObservableList<Track> tracks) {
        this.tracks.setAll(tracks);
        listView.setItems(tracks);
    }
}
