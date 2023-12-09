package hitbeat.view.base.widgets.generic_list;

import java.util.HashMap;
import java.util.Map;

import hitbeat.model.Track;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import hitbeat.view.tracks.TrackCell;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.mfxcore.controls.Text;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenericTrackList extends MFXScrollPane implements BaseView {
    private ObservableList<Track> tracks = FXCollections.observableArrayList();
    private final ListView<Track> listView;

    public GenericTrackList() {
        super(null);

        Text placeholder = new Text("Nenhuma música adicionada");
        placeholder.getStyleClass().add("placeholder");

        listView = new ListView<>(tracks, track -> {
            TrackCell trackCell = new TrackCell(track);
            trackCell.setOnTrackRemoved(removedTrack -> {
                this.tracks.remove(removedTrack);
            });
            return trackCell;
        });

        this.tracks.subscribe(() -> {
            if (tracks.size() == 0) {
                listView.setPlaceholder(placeholder);
            } else {
                listView.setPlaceholder(null);
                listView.setItems(tracks);
            }
        });

        listView.setPlaceholder(placeholder);

        this.setContent(listView);

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    @Override
    public Map<String, Object> getData() {
        return new HashMap<String, Object>() {{
            put("tracks", tracks);
        }};
    }

    public void setTracks(ObservableList<Track> tracks) {
        this.tracks.setAll(tracks);
    }
}
