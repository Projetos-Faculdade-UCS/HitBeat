package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.widgets.Margin;
import hitbeat.view.base.wrappers.Slider;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class ProgressBar extends HBox{
    Label minValueLabel;
    Label maxValueLabel;
    Slider progressSlider;
    
    private boolean isDragging = false;

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
        minValueLabel.getStyleClass().add("label");
        maxValueLabel.getStyleClass().add("label");
        progressSlider.getStyleClass().add("progress-slider");
        this.setId("progress-bar");

        // ------atualizador do tempo---------
        Timeline progressManager = player.getProgressManager(this);

        progressSlider.setOnMousePressed(event -> isDragging=true );
        progressSlider.setOnMouseReleased(event ->{
            player.seek(progressSlider.getValue());
            isDragging=false;
        });
        progressManager.setCycleCount(Timeline.INDEFINITE);
        progressManager.play();

        HBox.setHgrow(progressSlider, Priority.ALWAYS);
        Margin minLabel = new Margin(maxValueLabel, 0, 10, 0, 0);
        Margin maxLabel = new Margin(minValueLabel, 0, 0, 0, 6);

        getChildren().addAll(minLabel, progressSlider, maxLabel);
    }

    public void setProgressIndicators(double duracao, double tempoAtual) {
        if (isDragging){
            minValueLabel.setText( this.formatTime(progressSlider.getValue()) );
            return;
        }
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