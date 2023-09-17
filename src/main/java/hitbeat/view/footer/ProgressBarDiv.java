package hitbeat.view.footer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ProgressBarDiv extends HBox{
    
    public ProgressBarDiv(MediaPlayer mediaPlayer) {
        super(1);

        // ------infos de duracao---------
        Slider progressSlider = new Slider();
        progressSlider.setMin(0);
        progressSlider.setValue(0);
        
        Label minValueLabel = new Label(this.formatTime(progressSlider.getMin()));
        Label maxValueLabel = new Label();

        mediaPlayer.setOnReady(() -> {
            double duracao = mediaPlayer.getTotalDuration().toSeconds();
            maxValueLabel.setText(this.formatTime(duracao));
            progressSlider.setMax(duracao);
        });

        // ------estilos---------
        minValueLabel.getStyleClass().add("min-value-label");
        maxValueLabel.getStyleClass().add("max-value-label");
        progressSlider.getStyleClass().add("progress-slider");
        this.getStyleClass().add("progress-bar-div");


        // ------atualizador do tempo---------
        Timeline sliderUpdater = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                progressSlider.setValue(mediaPlayer.getCurrentTime().toSeconds());
                minValueLabel.setText(this.formatTime(progressSlider.getValue()));
            })
        );
        progressSlider.setOnMousePressed(event ->{
            sliderUpdater.pause();
        });
        progressSlider.setOnMouseReleased(event ->{
            mediaPlayer.seek(Duration.seconds(progressSlider.getValue()));
            sliderUpdater.play();
        });
        sliderUpdater.setCycleCount(Timeline.INDEFINITE);
        sliderUpdater.play();

        getChildren().addAll(minValueLabel, progressSlider, maxValueLabel);
    }
    
    private String formatTime(double time) {
        int seconds = (int) time;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
