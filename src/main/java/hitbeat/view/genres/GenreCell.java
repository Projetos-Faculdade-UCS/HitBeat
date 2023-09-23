package hitbeat.view.genres;

import hitbeat.model.Genre;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GenreCell extends HBox {
    private Genre genre;
    
    public GenreCell(Genre genre) {
        this.genre = genre;
        VBox contentBox = new VBox();
        contentBox.setSpacing(5); // Spacing between title and subtitle

        // Create Leading - It can be an ImageView or any other node.
        Node leading = new GenreCellCenter("lead").build(); // Assuming this method returns a Node that you want to use as leading.

        // Create Title
        Label titleLabel = new Label(genre.getName());
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;"); // Adjust styling as needed

        // Create Subtitle
        Label subtitleLabel = new Label("Subtitle text here"); // Replace with actual subtitle
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;"); // Adjust styling as needed

        contentBox.getChildren().addAll(titleLabel, subtitleLabel);

        // Construct the ListTile-like layout
        this.setSpacing(10); // Spacing between leading and the content box
        this.setPadding(new Insets(10, 10, 10, 10)); // Padding around the this

        this.getChildren().addAll(leading, contentBox);

        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(this, javafx.scene.layout.Priority.ALWAYS);
    }

}

