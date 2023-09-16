package hitbeat.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CenterOne extends VBox {

    private Label contentLabel;

    public CenterOne() {
        contentLabel = new Label("Welcome to the Index Page");
        getChildren().add(contentLabel);
        contentLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #2195f3;");
        // center
        setSpacing(10);
        setMinWidth(600);
        setMinHeight(600);

        getStyleClass().add("center-one");
    }

    // If you want to provide external access to the contentLabel
    public Label getContentLabel() {
        return contentLabel;
    }

    public void setContentText(String text) {
        contentLabel.setText(text);
    }
}
