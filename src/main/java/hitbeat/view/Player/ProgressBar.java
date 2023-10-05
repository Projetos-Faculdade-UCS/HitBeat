package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.wrappers.Slider;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class ProgressBar extends HBox{
    Label minValueLabel;
    Label maxValueLabel;
    Slider progressSlider;
    
    public ProgressBar() {
        super(1);

        PlayerController player = PlayerController.getInstance();
        
        // ------infos de duracao---------
        progressSlider = new Slider();
        progressSlider.setMin(0);
        progressSlider.setValue(0);
        minValueLabel = new Label("00:00");
        maxValueLabel = new Label("00:00");
        
        // ------estilos---------
        minValueLabel.getStyleClass().addAll("label", "px-2");
        maxValueLabel.getStyleClass().addAll("label", "px-1");
        progressSlider.getStyleClass().add("progress-slider");
        this.setId("progress-bar");

        // ------atualizador do tempo---------
        Timeline progressManager = player.getProgressManager(this);

        progressSlider.setOnMousePressed(event -> progressManager.pause() );
        progressSlider.setOnMouseReleased(event ->{
            player.seek(progressSlider.getValue());
            progressManager.play();
        });
        progressManager.setCycleCount(Timeline.INDEFINITE);
        progressManager.play();

        getChildren().addAll(minValueLabel, progressSlider, maxValueLabel);
    }

    public void setProgressIndicators(double duracao, double tempoAtual) {
        progressSlider.setMax(duracao);
        maxValueLabel.setText(this.formatTime(duracao));
        
        progressSlider.setValue(tempoAtual);
        minValueLabel.setText(this.formatTime(tempoAtual));
    }

    private String formatTime(double time) {
        int seconds = (int) time;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}