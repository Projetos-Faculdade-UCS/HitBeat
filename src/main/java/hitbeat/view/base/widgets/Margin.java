package hitbeat.view.base.widgets;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class Margin extends Pane {
    public Margin(Node child) {
        this.setPadding(new Insets(0));
        this.getChildren().add(child);
    }
    public Margin(Node child, double top, double right, double bottom, double left) {
        this.setPadding(new Insets(top, right, bottom, left));
        this.getChildren().add(child);
    }

    public void setMargin(double top, double right, double bottom, double left) {
        this.setPadding(new Insets(top, right, bottom, left));
    }
}
