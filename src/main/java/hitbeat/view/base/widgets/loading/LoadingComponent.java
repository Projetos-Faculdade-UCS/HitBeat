package hitbeat.view.base.widgets.loading;

import io.github.palexdev.mfxcore.controls.Text;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class LoadingComponent extends VBox {
    private PulsingIcon pulsingIcon;
    private boolean playSound = true;

    public LoadingComponent(boolean playSound) {
        super();
        this.playSound = playSound;

        // Create Text
        Text loadingText = new Text("Carregando...");
        loadingText.getStyleClass().add("loading-text");

        // StackPane to stack CircularProgressIndicator and loadingText
        this.setAlignment(Pos.CENTER); // Center the contents
        this.setSpacing(20);
        this.getChildren().addAll(pulsingIcon = new PulsingIcon(), loadingText);
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    public void dispose() {
        pulsingIcon.dispose();
        pulsingIcon = null;
    }

    private class PulsingIcon extends BorderPane {
        static final String ICON_PATH = "/hitbeat/images/hitbeat-icon.png";
        static final int ICON_SIZE = 200;
        private ScaleTransition scaleTransition;
        private ImageView imageView;
        private MediaPlayer pulseSound = new MediaPlayer(
                new Media(getClass().getResource("/hitbeat/audio/pulse.mp3").toString()));

        private Timeline pulseTimeline;

        public PulsingIcon() {
            super();

            // Create Icon
            Image icon = new Image(ICON_PATH, ICON_SIZE, ICON_SIZE, true, true);

            imageView = new ImageView(icon);

            // StackPane to stack Icon and ScaleTransition
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER); // Center the contents
            stackPane.getChildren().addAll(imageView);

            // Set center of the PulsingIcon to the StackPane
            this.setCenter(stackPane);

            // Add pulsing effect
            addPulsingEffect();
        }

        private void addPulsingEffect() {
            Duration pulseDuration = Duration.seconds(1);
            scaleTransition = new ScaleTransition(pulseDuration, imageView);
            scaleTransition.setFromX(1.0);
            scaleTransition.setFromY(1.0);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
            scaleTransition.setAutoReverse(true);

            Platform.runLater(() -> {
                if (playSound) {
                    pulseSound.setVolume(0.5);
                    // Manually handle the pulsing effect using a Timeline
                    pulseTimeline = new Timeline(
                            new KeyFrame(Duration.seconds(0), event -> {
                                pulseSound.seek(Duration.seconds(0));
                                pulseSound.play();
                            }),
                            new KeyFrame(pulseDuration));

                    pulseTimeline.setCycleCount(Timeline.INDEFINITE);
                    pulseTimeline.play();
                }
            });

            scaleTransition.play();
        }

        public void dispose() {
            scaleTransition.stop();
            scaleTransition = null;
            imageView = null;
            if (pulseTimeline != null)
                pulseTimeline.stop();
            pulseTimeline = null;
            pulseSound.stop();
            pulseSound.dispose();
            pulseSound = null;
        }
    }
}
