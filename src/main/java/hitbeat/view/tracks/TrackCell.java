package hitbeat.view.tracks;

import hitbeat.model.Track;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class TrackCell extends BaseCell<Track> {
    private Track track;
    private Label titleLabel;
    private Label subtitleLabel;
    private Label trailingLabel;

    public TrackCell(Track track) {
        // Initialize UI components
        initUI();

        // Set track data
        updateItem(track);
    }

    private void initUI() {
        // Create Leading
        Text leading = new Text("lead");
        leading.setStyle("-fx-font-size: 16; -fx-text-fill: white;");

        // Create Title
        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");

        // Create Subtitle
        subtitleLabel = new Label();
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        // Create Trailing
        trailingLabel = new Label();
        trailingLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        ListTile listTile = new ListTile(leading, titleLabel, subtitleLabel, trailingLabel);
        this.getChildren().add(listTile);
    }

    @Override
    public void updateItem(Track track) {
        this.track = track;

        if (track != null) {
            titleLabel.setText(this.track.getName());
            subtitleLabel.setText("Subtitle text here"); // Update if Track has more data
            trailingLabel.setText(track.getGenre().getName());
        } else {
            titleLabel.setText("");
            subtitleLabel.setText("");
            trailingLabel.setText("");
        }
    }
}
