package hitbeat.view.genres;

import hitbeat.controller.player.PlayerController;
import hitbeat.model.Genre;
import hitbeat.view.base.widgets.Cover;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.scene.control.Label;

public class GenreCell extends BaseCell<Genre> {
    private Genre genre;
    private Label titleLabel;
    private Label subtitleLabel;
    private Label trailingLabel;
    private Cover cover;

    public GenreCell(Genre genre) {
        // Initialize UI components
        initUI();

        // Set genre data
        updateItem(genre);
    }

    private void initUI() {
        cover = new Cover();
        cover.setFit(50);

        cover.setOnMouseClicked(event -> {
            PlayerController.getInstance().play(this.genre);
        });

        // Create Title
        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");

        // Create Subtitle
        subtitleLabel = new Label();
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        // Create Trailing
        trailingLabel = new Label();
        trailingLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        ListTile listTile = new ListTile(cover, titleLabel, subtitleLabel, trailingLabel);
        this.getChildren().add(listTile);

        // on hover, shows the play button
        this.setOnMouseEntered(e -> {
            cover.showPlayButton(true);
        });

        // on exit, hides the play button
        this.setOnMouseExited(e -> {
            cover.showPlayButton(false);
        });
    }

    @Override
    public void updateItem(Genre genre) {
        this.genre = genre;

        if (genre != null) {
            titleLabel.setText(this.genre.getName());
            subtitleLabel.setText("Subtitle text here"); // Update if Genre has more data
            trailingLabel.setText("Trail"); // Update if Genre has more data

            cover.setCoverImage(this.genre.getCover(50));
        } else {
            titleLabel.setText("");
            subtitleLabel.setText("");
            trailingLabel.setText("");
            cover.setCoverImage(null);
        }
    }
}
