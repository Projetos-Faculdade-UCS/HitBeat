package hitbeat.view.base.widgets;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ListTile extends HBox {
    public ListTile(Node leading, Node title, Node subtitle, Node trailing) {
        // Initialize ContentBox for title and subtitle
        VBox contentBox = new VBox(5); 
        if (title != null) contentBox.getChildren().add(title);
        if (subtitle != null) contentBox.getChildren().add(subtitle);

        // Create and add spacer for positioning
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Build the HBox
        if (leading != null) this.getChildren().add(leading);
        this.getChildren().addAll(contentBox, spacer);
        if (trailing != null) this.getChildren().add(trailing);

        // Styling
        styleHBox();

        // Add listener to parentProperty
        addParentPropertyListener();
    }

    private void styleHBox() {
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        VBox.setVgrow(this, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
    }

    private void addParentPropertyListener() {
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
        if (parentWidth <= 0) return; 
        this.setPrefWidth(parentWidth);
    }
}
