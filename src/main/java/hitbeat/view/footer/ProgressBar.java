package hitbeat.view.footer;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.wrappers.Slider;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


public class ProgressBar extends HBox{
    
    public ProgressBar(PlayerController player) {
        super(1);

        // ------infos de duracao---------
        Slider progressSlider = new Slider();
        progressSlider.setMin(0);
        progressSlider.setValue(0);
        
        Label minValueLabel;
        Label maxValueLabel;

        player.setOnReady(() -> {
            double duracao = player.getTotalDuration();
            progressSlider.setMax(duracao);
            maxValueLabel.setText(this.formatTime(duracao));
            minValueLabel.setText(this.formatTime(0));
        });

        // ------estilos---------
        minValueLabel.getStyleClass().addAll("label", "px-2");
        maxValueLabel.getStyleClass().addAll("label", "px-1");
        progressSlider.getStyleClass().add("progress-slider");
        this.setId("progress-bar");

        // ------atualizador do tempo---------
        Timeline sliderUpdater = new Timeline(
            new KeyFrame(Duration.seconds(.01), event -> {
                progressSlider.setValue(player.getCurrentTime());
                minValueLabel.setText(this.formatTime(progressSlider.getValue()));
            })
        );
        progressSlider.setOnMousePressed(event ->{
            sliderUpdater.pause();
        });
        progressSlider.setOnMouseReleased(event ->{
            player.seek(Duration.seconds(progressSlider.getValue()));
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