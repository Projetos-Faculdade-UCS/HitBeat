package hitbeat.view.base.widgets;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Cover extends StackPane {
    private ImageView cover = new ImageView();
    private Image image;
    private ImageView playButton = new ImageView();
    private double fitValue = 150;

    public Cover() {
        this.setPrefHeight(fitValue);
        this.setPrefWidth(fitValue);
        this.setStyle("-fx-background-color: #000000; -fx-background-radius: 10;");
        this.getChildren().addAll(cover, playButton);
        this.setAlignment(Pos.CENTER);
    }

    public void setCoverImage(Image image) {
        cover.setImage(image);
        cover.setFitWidth(fitValue);
        cover.setPreserveRatio(true);

        // on hover, shows the play button
        this.setOnMouseEntered(e -> {
            showPlayButton(true);
        });

        // on exit, hides the play button
        this.setOnMouseExited(e -> {
            showPlayButton(false);
        });
    }

    public void setFit(double width) {
        fitValue = width;
        this.setPrefWidth(width);
        cover.setFitWidth(width);
        this.setPrefHeight(width);
    }

    public void showPlayButton(boolean show) {
        if (show) {
            image = new Image("/hitbeat/images/play.png", fitValue, fitValue, false, true);
            playButton.setImage(image);
            playButton.setFitWidth(fitValue);
            playButton.setPickOnBounds(true);
        } else {
            playButton.setImage(null);
        }
        

        // Create and configure the drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        // dropShadow.setOffsetX(15.0);
        // dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.BLACK);

        // Apply the drop shadow effect to the ImageView
        playButton.setEffect(dropShadow);
    }

    public void setPlayButtonAction(Runnable action) {
        playButton.setOnMouseClicked(e -> {
            action.run();
        });
    }

}
