package hitbeat.view;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LoadingPage extends BorderPane {
    private MediaPlayer cracklingSound = new MediaPlayer(
            new Media(getClass().getResource("/hitbeat/audio/fire.mp3").toString()));

    private PulsingIcon pulsingIcon;

    public LoadingPage() {
        super();

        pulsingIcon = new PulsingIcon();

        // Create Text
        Text loadingText = new Text("Carregando...");
        loadingText.getStyleClass().add("loading-text");

        // StackPane to stack CircularProgressIndicator and loadingText
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER); // Center the contents
        vbox.setSpacing(20);
        vbox.getChildren().addAll(pulsingIcon, loadingText);

        // Set center of the LoadingPage to the vbox
        this.setCenter(vbox);

        // Play crackling sound
        cracklingSound.setVolume(0.5);
        cracklingSound.setCycleCount(MediaPlayer.INDEFINITE);
        cracklingSound.seek(Duration.seconds(1));
        cracklingSound.play();
    }

    private class PulsingIcon extends BorderPane {
        static final String ICON_PATH = "/hitbeat/images/hitbeat-icon.png";
        static final int ICON_SIZE = 200;
        private ScaleTransition scaleTransition;
        private ImageView imageView;
        private MediaPlayer pulseSound = new MediaPlayer(
                new Media(getClass().getResource("/hitbeat/audio/pulse.mp3").toString()));

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
            Duration duration = Duration.seconds(.8);
            scaleTransition = new ScaleTransition(duration, imageView);
            scaleTransition.setFromX(1.0);
            scaleTransition.setFromY(1.0);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
            scaleTransition.setAutoReverse(true);

            pulseSound.setVolume(0.5);

            scaleTransition.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue.toSeconds() == 0.0) {
                    pulseSound.seek(Duration.seconds(0));
                    pulseSound.play();
                }
            });

            scaleTransition.play();
        }

        public void dispose() {
            scaleTransition.stop();
            scaleTransition = null;
            imageView = null;
            pulseSound.stop();
            pulseSound.dispose();
            pulseSound = null;
        }
    }

    public void dispose() {
        stopCracklingSound();
        pulsingIcon.dispose();
        pulsingIcon = null;
    }

    private void stopCracklingSound() {
        cracklingSound.stop();
        cracklingSound.dispose();
        cracklingSound = null;
    }
}
