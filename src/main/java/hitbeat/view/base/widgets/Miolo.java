package hitbeat.view.base.widgets;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Miolo extends StackPane {
    FabContainer fab = new FabContainer();
    Node content = new VBox();

    public Miolo() {
        super();

        this.setAlignment(Pos.BOTTOM_RIGHT);

        // fab should be always on top, then the content
        this.getChildren().addAll(this.content, this.fab);
    }

    public void setFab(FloatingActionButton fab) {
        this.fab.setFab(fab);

        removeFab();

        this.getChildren().add(this.fab);
    }

    public void removeFab() {
        this.getChildren().remove(this.fab);
    }

    public void setContent(Node content) {
        // remove the content from the stack pane
        this.getChildren().remove(this.content);

        // set the new content
        this.content = content;

        // add the new content to the stack pane
        this.getChildren().add(this.content);
    }

    class FabContainer extends HBox {
        public FabContainer() {
            super();
            this.setAlignment(Pos.BOTTOM_RIGHT);
            this.setPadding(new Insets(8, 24, 0, 0));
            // enable click only in the fab, not in the container
            this.setPickOnBounds(false);
        }

        public void setFab(FloatingActionButton fab) {
            // Remove the previous fab
            this.getChildren().clear();

            this.getChildren().add(fab);
            this.setAlignment(Pos.BOTTOM_RIGHT);
        }
    }
}
