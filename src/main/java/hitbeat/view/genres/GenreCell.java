package hitbeat.view.genres;

import hitbeat.controller.player.PlayerController;
import hitbeat.model.Genre;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.RoundedButton;
import hitbeat.view.base.widgets.SVGWidget;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
        RoundedButton playBtn = new RoundedButton("");
        SVGWidget svgPlay = new SVGWidget("/hitbeat/svg/play.svg", 25, Color.WHITE);
        playBtn.setGraphic(svgPlay);

        StackPane playbox = new StackPane(playBtn);
        VBox.setVgrow(playbox, Priority.ALWAYS);

        playbox.setAlignment(Pos.CENTER);
        playbox.setPickOnBounds(false);

        playBtn.setOnMouseClicked(event -> {
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

        ListTile listTile = new ListTile(playbox, titleLabel, subtitleLabel, trailingLabel);
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
