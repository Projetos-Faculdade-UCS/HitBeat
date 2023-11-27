package hitbeat.view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoadingPage extends BorderPane {
    public LoadingPage() {
        super();

        // Create CircularProgressIndicator
        CircularProgressIndicator circularProgressIndicator = new CircularProgressIndicator();

        // Create Text
        Text loadingText = new Text("Loading...");
        loadingText.getStyleClass().add("loading-text");

        // StackPane to stack CircularProgressIndicator and loadingText
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER); // Center the contents
        stackPane.getChildren().addAll(circularProgressIndicator, loadingText);

        // Set center of the LoadingPage to the StackPane
        this.setCenter(stackPane);
    }

    private class CircularProgressIndicator extends Pane {

        private static final double SIZE = 50; // Set the size of the circular progress indicator
        private static final double STROKE_WIDTH = 5; // Set the stroke width of the circular progress indicator
        private static final double ARC_RADIUS = (SIZE - STROKE_WIDTH) / 2; // Calculate the radius of the circular arc

        public CircularProgressIndicator() {
            // Create a path representing a half-circle
            Path path = createCircularPath();

            // Set the stroke properties
            path.setStroke(Color.BLUE);
            path.setStrokeWidth(STROKE_WIDTH);
            path.setStrokeLineCap(StrokeLineCap.ROUND);
            path.setFill(null);

            // Create a rotate transition for continuous rotation
            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), path);
            rotateTransition.setByAngle(360); // Rotate by 360 degrees
            rotateTransition.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
            rotateTransition.setInterpolator(Interpolator.LINEAR); // Use a linear interpolator

            // Start the rotation animation
            rotateTransition.play();

            // Add the path to the CircularProgressIndicator
            getChildren().add(path);

            // Center the shape within the region
            setCenterShape(true);
        }

        private Path createCircularPath() {
            // Create a path representing a half-circle using MoveTo and ArcTo
            Path path = new Path();
            path.getElements().add(new MoveTo(ARC_RADIUS + STROKE_WIDTH, ARC_RADIUS + STROKE_WIDTH));
            path.getElements().add(new ArcTo(ARC_RADIUS, ARC_RADIUS, 0, ARC_RADIUS, 0, true, true));
            return path;
        }

        @Override
        protected double computePrefWidth(double height) {
            return SIZE;
        }

        @Override
        protected double computePrefHeight(double width) {
            return SIZE;
        }
    }
}
