package hitbeat.view;

import hitbeat.view.base.widgets.loading.LoadingComponent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class LoadingPage extends BorderPane {
    private MediaPlayer cracklingSound = new MediaPlayer(
            new Media(getClass().getResource("/hitbeat/audio/fire.mp3").toString()));

    private LoadingComponent pulsingIcon;

    private boolean playSound = true;

    public LoadingPage() {
        super();

        pulsingIcon = new LoadingComponent();

        // Set center of the LoadingPage to the vbox
        this.setCenter(pulsingIcon);

        if (playSound) {
            playCracklingSound();
        }
    }

    public void setPlaySound(boolean playSound) {
        this.playSound = playSound;
    }

    private void playCracklingSound() {
        cracklingSound.setVolume(0.5);
        cracklingSound.setCycleCount(MediaPlayer.INDEFINITE);
        cracklingSound.seek(Duration.seconds(1));
        cracklingSound.play();
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
