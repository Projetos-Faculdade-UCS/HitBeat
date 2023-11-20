package hitbeat.view.base.widgets;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cover extends StackPane {
    private ImageView cover = new ImageView();
    private Image image;
    private ImageView playButton = new ImageView();
    private double fitValue = 150;
    private Rectangle retangulo;

    public Cover() {
        this.setPrefHeight(fitValue);
        this.setPrefWidth(fitValue);
        this.getStyleClass().addAll("transparent", "rounded-sm");
        this.getChildren().addAll(cover, playButton);
        this.setAlignment(Pos.CENTER);
    }

    public void setCoverImage(Image image) {
        this.image = image;
        cover.setImage(this.image);
        cover.setFitWidth(fitValue);
        cover.setPreserveRatio(true);

        retangulo = new Rectangle(fitValue, fitValue);
        retangulo.setArcWidth(10);
        retangulo.setArcHeight(10);

        cover.setClip(retangulo);
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

            playButton.setCursor(Cursor.HAND);
        } else {
            playButton.setImage(null);
        }

        // Create and configure the drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5);
        dropShadow.setColor(Color.BLACK);

        // Apply the drop shadow effect to the ImageView
        playButton.setEffect(dropShadow);
    }

    public void setPlayButtonAction(Runnable action) {
        playButton.setOnMouseClicked(e -> {
            action.run();
        });
    }

    public boolean isMouseOverPlayButton() {
        return playButton.isHover();
    }

}
