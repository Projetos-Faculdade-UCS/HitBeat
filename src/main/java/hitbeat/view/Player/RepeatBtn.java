package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.controller.player.RepeatMode;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class RepeatBtn extends MFXButton {
    public RepeatBtn() {
        super("");
        this.getStyleClass().add("repeat-btn");

        PlayerController player = PlayerController.getInstance();

        SVGWidget svgRepeat = new SVGWidget(
                "/hitbeat/svg/repeat.svg", 18, Color.WHITE);

        Circle repeatIndicator = new Circle(2);
        repeatIndicator.getStyleClass().add("repeat-indicator");

        Text repeatOne = new Text("1");
        repeatOne.getStyleClass().add("repeat-one");

        StackPane.setAlignment(svgRepeat, Pos.CENTER);
        StackPane.setAlignment(repeatIndicator, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(repeatOne, Pos.CENTER);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(svgRepeat, repeatIndicator, repeatOne);

        player.setOnRepeat(repeat -> {
            setRepeatOne(repeat == RepeatMode.REPEAT_ONE);
            setRepeat(repeat == RepeatMode.REPEAT_ALL || repeat == RepeatMode.REPEAT_ONE);
        });
        this.setOnMouseClicked(event -> player.toggleRepeat());
        this.setGraphic(stack);
    }

    private void setRepeatOne(boolean repeatOne) {
        if (repeatOne) {
            this.getStyleClass().add("repeat-one");
        } else {
            this.getStyleClass().remove("repeat-one");
        }
    }

    private void setRepeat(boolean repeat) {
        if (repeat) {
            this.addRepeat();
        } else {
            this.removeRepeat();
        }
    }

    private void addRepeat() {
        this.getStyleClass().remove("repeat");
        this.getStyleClass().add("repeat");
    }

    private void removeRepeat() {
        this.getStyleClass().remove("repeat");
    }

}