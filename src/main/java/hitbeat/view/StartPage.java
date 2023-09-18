package hitbeat.view;

import hitbeat.view.base.widgets.Widget;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class StartPage extends Widget {

    private Label contentLabel;

    public StartPage() {
        contentLabel = new Label("Welcome to the HitBeat!");
    }

    @Override
    public Node build() {
        contentLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #2195f3;");

        Pane container = new Pane(contentLabel);

        contentLabel.layoutXProperty().bind(
                container.widthProperty().subtract(contentLabel.widthProperty()).divide(2));
        contentLabel.layoutYProperty().bind(
                container.heightProperty().subtract(contentLabel.heightProperty()).divide(2));

        return container;
    }

    public void setContentText(String text) {
        contentLabel.setText(text);
    }
}
