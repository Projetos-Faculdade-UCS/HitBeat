package hitbeat.view.footer;


import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class RepeatBtn extends MFXButton{
    private boolean repeat = false;

    public RepeatBtn(MediaPlayer mediaPlayer){
        super("");

        SVGWidget svgRepeat = new SVGWidget("/hitbeat/svg/repeat.svg", 17, Color.WHITE);
        Node repeatNode = svgRepeat.build();
        
        Circle repeatIndicator = new Circle(2);
        repeatIndicator.setFill(Color.WHITE);
        repeatIndicator.setVisible(false);

        StackPane.setAlignment(repeatNode, Pos.CENTER);
        StackPane.setAlignment(repeatIndicator, Pos.BOTTOM_CENTER);
        repeatIndicator.setStyle("-fx-translate-y: 10px;");

        StackPane stack = new StackPane();
        stack.getChildren().addAll(repeatNode, repeatIndicator);

        this.setGraphic(stack);
        this.setOnAction(event -> {
            if (repeat == false) {
                repeat = true;
                repeatIndicator.setVisible(true);
                repeatIndicator.setFill(Color.PURPLE);

                svgRepeat.setColor(Color.PURPLE);
                setNewRepeat(stack, svgRepeat.build());

                mediaPlayer.setOnEndOfMedia(() -> {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    mediaPlayer.play();
                });
            } else {
                repeat = false;
                repeatIndicator.setVisible(false);
                repeatIndicator.setFill(Color.WHITE);

                svgRepeat.setColor(Color.WHITE);
                setNewRepeat(stack, svgRepeat.build());

                mediaPlayer.setOnEndOfMedia(() -> {});
            }
        });
    }

    private void setNewRepeat(StackPane stack, Node repeatNode) {
        stack.getChildren().set(0, repeatNode);
    }

}