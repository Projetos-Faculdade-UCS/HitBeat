package hitbeat.view.base.widgets;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ListTile extends HBox {
    private boolean isResizing = false;

    public ListTile(Node leading, Node title, Node subtitle, Node trailing) {
        // ContentBox for holding title and subtitle
        VBox contentBox = new VBox();
        contentBox.setSpacing(5); // Spacing between title and subtitle

        // Add title and subtitle nodes to contentBox
        if (title != null) {
            contentBox.getChildren().add(title);
        }

        if (subtitle != null) {
            contentBox.getChildren().add(subtitle);
        }

        // Create spacers to position elements
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS); 

        // Add to the main HBox
        if (leading != null) {
            this.getChildren().add(leading);
        }

        this.getChildren().addAll(contentBox, spacer);  // Spacer1 is added before contentBox and spacer2 is added after contentBox

        if (trailing != null) {
            this.getChildren().add(trailing);
        }

        // Styling and layout
        this.setSpacing(10); // Spacing between nodes
        this.setPadding(new Insets(10, 10, 10, 10)); // Padding around the HBox

        VBox.setVgrow(this, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);

        this.parentProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) { // If attached to a new parent
                // Listen to width changes of the new parent
                newValue.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
                    adjustWidthBasedOnParent(newBounds.getWidth());
                });
                
                // Immediately adjust width based on current parent width
                adjustWidthBasedOnParent(newValue.getLayoutBounds().getWidth());
            }
        });
    }

    private void adjustWidthBasedOnParent(double parentWidth) {
        if (!isResizing && parentWidth > 0) {
            isResizing = true;
            this.setPrefWidth(parentWidth);
            isResizing = false;
        }
    }
}
