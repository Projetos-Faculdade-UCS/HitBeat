package hitbeat.view.footer;


import hitbeat.styles.Styles;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;


public class RepeatBtn extends Button{
    private boolean repeat = false;

    public RepeatBtn(MediaPlayer mediaPlayer){
        super("");

        SVGPath svg = new SVGPath();
        svg.setContent("M11 5.466V4H5a4 4 0 0 0-3.584 5.777.5.5 0 1 1-.896.446A5 5 0 0 1 5 3h6V1.534a.25.25 0 0 1 .41-.192l2.36 1.966c.12.1.12.284 0 .384l-2.36 1.966a.25.25 0 0 1-.41-.192Zm3.81.086a.5.5 0 0 1 .67.225A5 5 0 0 1 11 13H5v1.466a.25.25 0 0 1-.41.192l-2.36-1.966a.25.25 0 0 1 0-.384l2.36-1.966a.25.25 0 0 1 .41.192V12h6a4 4 0 0 0 3.585-5.777.5.5 0 0 1 .225-.67Z");
        svg.setFill(Color.WHITE);
        
        Circle repeatIndicator = new Circle(2);
        repeatIndicator.setFill(Color.WHITE);
        repeatIndicator.setVisible(false);

        StackPane.setAlignment(svg, Pos.CENTER);
        StackPane.setAlignment(repeatIndicator, Pos.BOTTOM_CENTER);
        repeatIndicator.setStyle("-fx-translate-y: 10px;");

        StackPane stack = new StackPane();
        stack.getChildren().addAll(svg, repeatIndicator);

        this.setGraphic(stack);
        this.setStyle(Styles.REPEAT_BTN);
        this.setOnAction(event -> {
            if (repeat == false) {
                repeat = true;
                repeatIndicator.setVisible(true);
                mediaPlayer.setOnEndOfMedia(() -> {
                    mediaPlayer.seek(mediaPlayer.getStartTime());
                    mediaPlayer.play();
                });
            } else {
                repeat = false;
                repeatIndicator.setVisible(false);
                mediaPlayer.setOnEndOfMedia(() -> {});
            }
        });
    }
}
