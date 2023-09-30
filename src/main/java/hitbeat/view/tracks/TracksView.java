package hitbeat.view.tracks;

import hitbeat.controller.tracks.TracksController;
import hitbeat.model.Track;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.Widget;
import hitbeat.view.base.widgets.listview.ListView;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TracksView extends Widget {
    private ObservableList<Track> tracks;
    private final TracksController controller = new TracksController();
    
    @Override
    public Node build() {
        tracks = controller.fetchAll();
        
        ListView<Track> listView = new ListView<Track>(tracks);

        listView.setCellFactory(track -> {
            return new ListCell<Track>() {
                @Override
                protected void updateItem(Track track, boolean empty) {
                    super.updateItem(track, empty);
                    if (track == null || empty) {
                        setText(null);
                        setGraphic(null);
                        // hide the cell
                        setId("hidden-list-cell");
                        return;
                    } else {        
                        VBox vbox = new VBox();
                        Text trackName = new Text(track.getName());
                        Text leading = new Text("Leading: " + track.getName());

                        Node trackCell = new ListTile(leading, trackName, leading, leading);
                        VBox.setVgrow(trackCell, Priority.ALWAYS);
                        HBox.setHgrow(trackCell, Priority.ALWAYS);
                        vbox.getChildren().add(trackCell);
        
                        HBox.setHgrow(vbox, Priority.ALWAYS);
                        
                        setGraphic(vbox);
                        setOnMouseClicked(event -> {
                            System.out.println("Clicked on " + track.getName());
                        });
                        setId("list-cell");
                    }
                }
            };
        });

        return listView;
    }
}
