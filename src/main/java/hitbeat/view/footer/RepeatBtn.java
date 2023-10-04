package hitbeat.view.footer;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RepeatBtn extends MFXButton {
    private boolean repeat = false;

    public RepeatBtn(PlayerController player) {
        super("");

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

        this.setGraphic(stack);
        this.setOnAction(event -> {
            if (repeat == false) {
                repeat = true;
                repeatIndicator.setVisible(true);
                repeatIndicator.setFill(Color.BLUEVIOLET);

                svgRepeat.setColor(Color.BLUEVIOLET);
                player.toggleRepeat();
                
            } else {
                repeat = false;
                repeatIndicator.setVisible(false);
                repeatIndicator.setFill(Color.WHITE);

                svgRepeat.setColor(Color.WHITE);
                player.toggleRepeat();
            }
        });
    }

}