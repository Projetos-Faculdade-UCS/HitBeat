package hitbeat.view.tracks;

import hitbeat.controller.tracks.TracksController;
import hitbeat.model.Track;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;

public class TracksView extends MFXScrollPane {
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

    // class ListTrackCell extends ListCell<Track> {
    //     private TrackCell trackCell;

    //     public ListTrackCell() {
    //         super();
    //         this.trackCell = new TrackCell(null);
    //     }

    //     @Override
    //     protected void updateItem(Track track, boolean empty) {
    //         super.updateItem(track, empty);
    //         if (track == null || empty) {
    //             trackCell.prefWidthProperty().unbind();
    //             setText(null);
    //             setGraphic(null);
    //             // hide the cell
    //             setId("hidden-list-cell");
    //             return;
    //         } else {
    //             trackCell.setTrack(track);
    //             trackCell.prefWidthProperty().bind(Layout.getInstance().getContentWidth().subtract(20));

    //             Margin margin = new Margin(trackCell, 0, 0, 8, 0);

    //             setGraphic(margin);
    //             setOnMouseClicked(event -> {
    //                 System.out.println("Clicked on " + track.getName());
    //             });
    //             trackCell.setId("list-cell");
    //         }
    //     }
    // }
}
