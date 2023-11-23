package hitbeat.view.playlists;

import hitbeat.controller.playlist.PlaylistController;
import hitbeat.model.Playlist;
import hitbeat.model.Track;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.listview.ListView;
import hitbeat.view.tracks.TrackCell;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DetailPlaylist extends MFXScrollPane implements BaseView{
    private ObservableList<Track> tracks;
    private final PlaylistController controller = new PlaylistController();
    private Playlist playlist;

    public DetailPlaylist(Playlist playlist) {
        super(null);
        this.playlist = playlist;
        tracks = controller.getAllTracks(playlist);

        ListView<Track> listView = new ListView<>(tracks, track -> {
            return new TrackCell(track);
        });

        listView.setItems(tracks);

        HBox playlistHeader = new HBox();
        playlistHeader.setPrefHeight(50);
        Text title = new Text(playlist.getName());
        title.setFill(Color.WHITE);
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 24;");
        playlistHeader.getChildren().add(title);

        this.setContent(new VBox(playlistHeader, listView));

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    @Override
    public Object getData() {
        return this.playlist;
    }

}
