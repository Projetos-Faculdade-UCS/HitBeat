package hitbeat.view.genres;

import hitbeat.model.Genre;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class GenreCell extends BaseCell<Genre> {
    private Genre genre;
    private Label titleLabel;
    private Label subtitleLabel;
    private Label trailingLabel;

    public GenreCell(Genre genre) {
        // Initialize UI components
        initUI();

        // Set genre data
        updateItem(genre);
    }

    private void initUI() {
        // Create Leading
        Node leading = new GenreCellCenter("lead");

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
    public void updateItem(Genre genre) {
        this.genre = genre;

        if (genre != null) {
            titleLabel.setText(this.genre.getName());
            subtitleLabel.setText("Subtitle text here"); // Update if Genre has more data
            trailingLabel.setText("Trail"); // Update if Genre has more data
        } else {
            titleLabel.setText("");
            subtitleLabel.setText("");
            trailingLabel.setText("");
        }
    }
}
