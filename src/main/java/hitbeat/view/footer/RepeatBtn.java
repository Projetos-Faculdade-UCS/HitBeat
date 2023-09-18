package hitbeat.view.footer;


import hitbeat.styles.Styles;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class RepeatBtn extends MFXButton{
    private boolean repeat = false;

    public RepeatBtn(MediaPlayer mediaPlayer){
        super("");
        Image repeatImage = Svg2Image.convert(getClass().getResourceAsStream("/hitbeat/images/repeat.svg"));
        ImageView repeatView = new ImageView(repeatImage);
        
        Circle repeatIndicator = new Circle(2);
        repeatIndicator.setFill(Color.WHITE);
        repeatIndicator.setVisible(false);

        StackPane.setAlignment(repeatView, Pos.TOP_CENTER);
        StackPane.setAlignment(repeatIndicator, Pos.BOTTOM_CENTER);
        StackPane.setMargin(repeatView, new Insets(0, 0, 10, 0));

        StackPane stack = new StackPane();
        stack.getChildren().addAll(repeatView, repeatIndicator);

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
