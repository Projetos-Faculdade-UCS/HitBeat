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

        PlayerController player = PlayerController.getInstance();

        SVGWidget svgRepeat = new SVGWidget(
                "/hitbeat/svg/repeat.svg", 16, Color.WHITE);

        Circle repeatIndicator = new Circle(2);
        repeatIndicator.setFill(Color.WHITE);
        repeatIndicator.setVisible(false);

        Text repeatOne = new Text("1");
        repeatOne.setFill(Color.WHITE);
        repeatOne.setVisible(false);

        StackPane.setAlignment(svgRepeat, Pos.CENTER);
        StackPane.setAlignment(repeatIndicator, Pos.BOTTOM_CENTER);
        repeatIndicator.setStyle("-fx-translate-y: 8px;");

        StackPane stack = new StackPane();
        stack.getChildren().addAll(svgRepeat, repeatIndicator);

        player.setOnRepeat(repeat -> {
            if (repeat == RepeatMode.REPEAT_ALL) {
                repeatIndicator.setVisible(true);
                repeatIndicator.setFill(Color.BLUEVIOLET);
                svgRepeat.setColor(Color.BLUEVIOLET);
                repeatOne.setVisible(false);
                stack.getChildren().remove(repeatOne);
            }else if (repeat == RepeatMode.REPEAT_ONE) {
                repeatIndicator.setVisible(true);
                repeatIndicator.setFill(Color.BLUEVIOLET);
                svgRepeat.setColor(Color.BLUEVIOLET);
                repeatOne.setVisible(true);
                stack.getChildren().add(repeatOne);
            } 
            else {
                repeatIndicator.setVisible(false);
                repeatIndicator.setFill(Color.WHITE);
                svgRepeat.setColor(Color.WHITE);
                repeatOne.setVisible(false);
                stack.getChildren().remove(repeatOne);
            }
        });
        this.setOnMouseClicked(event -> player.toggleRepeat());
        this.setGraphic(stack);
    }

}