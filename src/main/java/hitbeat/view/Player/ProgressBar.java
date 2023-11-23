package hitbeat.view.Player;

import hitbeat.controller.player.PlayerController;
import hitbeat.view.base.widgets.Margin;
import hitbeat.view.base.wrappers.Slider;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ProgressBar extends HBox {
    private final Label minValueLabel;
    private final Label maxValueLabel;
    private final Slider progressSlider;

    private volatile boolean isDragging = false;

    public ProgressBar() {
        super(1);

        PlayerController player = PlayerController.getInstance();

        // Initialize UI components once to avoid unnecessary object creation.
        progressSlider = new Slider();
        minValueLabel = new Label("00:00");
        maxValueLabel = new Label("00:00");

        // Style setup
        applyStyles();

        // Event handlers setup
        setupEventHandlers(player);

        // Layout setup
        layoutComponents();
    }

    private void applyStyles() {
        minValueLabel.getStyleClass().add("label");
        maxValueLabel.getStyleClass().add("label");
        progressSlider.getStyleClass().add("progress-slider");
        this.setId("progress-bar");
    }

    private void setupEventHandlers(PlayerController player) {
        player.setOnSongStart(song -> Platform.runLater(() -> {
            double duration = song.getDuration().toSeconds();
            progressSlider.setMin(0);
            progressSlider.setMax(duration);
            progressSlider.setValue(0);
            minValueLabel.setText("00:00");
            maxValueLabel.setText(formatTime(duration));
        }));

        player.setOnProgress(progress -> Platform.runLater(() -> {
            if (!isDragging) {
                double currentTime = progress.getCurrentTime();
                progressSlider.setValue(currentTime);
                minValueLabel.setText(formatTime(currentTime));
            }
        }));

        progressSlider.setOnMousePressed(event -> isDragging = true);

        progressSlider.setOnMouseReleased(event -> {
            player.seek(progressSlider.getValue());
            isDragging = false;
        });
    }

    private void layoutComponents() {
        HBox.setHgrow(progressSlider, Priority.ALWAYS);
        Margin minLabel = new Margin(minValueLabel, 0, 10, 0, 0);
        Margin maxLabel = new Margin(maxValueLabel, 0, 0, 0, 6);
        getChildren().addAll(minLabel, progressSlider, maxLabel);
    }

    public void setProgressIndicators(double duration, double currentTime) {
        Platform.runLater(() -> {
            if (!isDragging) {
                progressSlider.setMax(duration);
                progressSlider.setValue(currentTime);
                minValueLabel.setText(formatTime(currentTime));
                maxValueLabel.setText(formatTime(duration));
            } else {
                minValueLabel.setText(formatTime(progressSlider.getValue()));
            }
        });
    }

    private String formatTime(double time) {
        int seconds = (int) time;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
