package hitbeat.view;

import java.util.Map;

import hitbeat.controller.tracks.TracksController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartPage extends Pane implements BaseView {

    private Label contentLabel;
    private int labalHighDiff = 0;
    private VBox tutorialCard;
    private TracksController tracksController = new TracksController();

    public StartPage() {
        contentLabel = new Label("Bem vindo ao HitBeat!");

        contentLabel.getStyleClass().add("welcome-text-label");

        this.getChildren().add(contentLabel);
        if (tracksController.fetchAll().size() == 0) {
            this.getChildren().add(getTutorialCard());
            labalHighDiff = 100;
        }

        contentLabel.layoutXProperty().bind(
                this.widthProperty().subtract(contentLabel.widthProperty()).divide(2));
        contentLabel.layoutYProperty().bind(
                this.heightProperty().subtract(contentLabel.heightProperty()).divide(2).subtract(labalHighDiff));

        // add stylesheets
        this.getStylesheets().add("/hitbeat/css/start-page/start-page.css");
    }

    public void setContentText(String text) {
        contentLabel.setText(text);
    }

    @Override
    public Map<String, Object> getData() {
        return null;
    }

    public VBox getTutorialCard() {
        tutorialCard = new VBox();
        tutorialCard.setSpacing(10);
        tutorialCard.getStyleClass().add("tutorial-card");

        Label tutorialLabel = new Label("Comece adicionando músicas! :D");
        tutorialLabel.getStyleClass().addAll("tutorial-label", "main-text");

        Label tutorialContentLabel = new Label(
                "1. Entre em 'Minha Biblioteca'.\n2. Adicione uma pasta.\n3. Clique no disquete para salvar.");
        tutorialContentLabel.getStyleClass().addAll("tutorial-content-label", "main-text");
        tutorialCard.getChildren().addAll(tutorialLabel, tutorialContentLabel);

        // center the tutorial card
        tutorialCard.layoutXProperty().bind(
                this.widthProperty().subtract(tutorialCard.widthProperty()).divide(2));
        tutorialCard.layoutYProperty().bind(
                this.heightProperty().subtract(tutorialCard.heightProperty()).divide(2));

        return tutorialCard;
    }
}
