package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RepeatBtn extends MFXButton {
    public RepeatBtn() {
        super("");

        PlayerController player = PlayerController.getInstance();

        SVGWidget svgRepeat = new SVGWidget(
                "/hitbeat/svg/repeat.svg", 16, Color.WHITE);

        Circle repeatIndicator = new Circle(2);
        repeatIndicator.setFill(Color.WHITE);
        repeatIndicator.setVisible(false);

        StackPane.setAlignment(svgRepeat, Pos.CENTER);
        StackPane.setAlignment(repeatIndicator, Pos.BOTTOM_CENTER);
        repeatIndicator.setStyle("-fx-translate-y: 8px;");

        StackPane stack = new StackPane();
        stack.getChildren().addAll(svgRepeat, repeatIndicator);

        player.setOnRepeat(repeat -> {
            if (repeat == true) {
                repeatIndicator.setVisible(true);
                repeatIndicator.setFill(Color.BLUEVIOLET);
                svgRepeat.setColor(Color.BLUEVIOLET);
            } else {
                repeatIndicator.setVisible(false);
                repeatIndicator.setFill(Color.WHITE);
                svgRepeat.setColor(Color.WHITE);
            }
        });
        this.setOnMouseClicked(event -> player.toggleRepeat());
        this.setGraphic(stack);
    }

}