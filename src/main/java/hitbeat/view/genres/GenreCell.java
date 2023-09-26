package hitbeat.view.genres;

import hitbeat.model.Genre;
import hitbeat.view.base.widgets.ListTile;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GenreCell extends Pane {
    private Genre genre;

    public GenreCell(Genre genre) {
        this.genre = genre;

        // Create Leading
        Node leading = new GenreCellCenter("lead").build();

        // Create Title
        Label titleLabel = new Label(this.genre.getName());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");

        // Create Subtitle
        Label subtitleLabel = new Label("Subtitle text here");
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        // Create Trailing
        Label trailingLabel = new Label("Trail");
        trailingLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        ListTile listTile = new ListTile(leading, titleLabel, subtitleLabel, trailingLabel);
        
        this.getChildren().add(listTile);
        VBox.setVgrow(this, Priority.ALWAYS);
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}
