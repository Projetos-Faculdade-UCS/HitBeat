package hitbeat.view.base.widgets;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FloatingActionButton extends Pane {
    MFXButton button = new MFXButton();

    public FloatingActionButton() {
        super();
        button.getStyleClass().add("fab");
        button.setText("");
        this.getChildren().add(button);
    }

    public FloatingActionButton(Image icon) {
        super();
        button.setGraphic(new ImageView(icon));
        button.getStyleClass().add("fab");
        button.setText("");
        this.getChildren().add(button);
    }

    public void setIcon(Image icon) {
        button.setGraphic(new ImageView(icon));
    }

    public void setIcon(SVGWidget icon) {
        button.setGraphic(icon);
    }

    public void setOnAction(EventHandler<ActionEvent> action) {
        button.setOnAction(action);
    }
}
