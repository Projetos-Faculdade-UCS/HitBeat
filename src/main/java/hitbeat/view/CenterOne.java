package hitbeat.view;

import hitbeat.view.base.widgets.Widget;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class CenterOne extends Widget {

    private static final String CONTENT_LABEL_STYLE = "-fx-font-size: 20px; -fx-text-fill: #2195f3;";

    private Label contentLabel;

    @Override
    public Node build() {
        getLabel().setStyle(CONTENT_LABEL_STYLE);

        Pane container = new Pane(getLabel());

        centerLabelInPane(container);

        return container;
    }

    private Label getLabel() {
        if (contentLabel == null) {
            contentLabel = new Label("Welcome to the CenterOne!");
        }
        return contentLabel;
    }

    private void centerLabelInPane(Pane container) {
        contentLabel.layoutXProperty().bind(
                container.widthProperty().subtract(contentLabel.widthProperty()).divide(2));
        contentLabel.layoutYProperty().bind(
                container.heightProperty().subtract(contentLabel.heightProperty()).divide(2));
    }

    public void setContentText(String text) {
        getLabel().setText(text);
    }
}
