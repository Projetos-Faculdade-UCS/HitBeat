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

        contentLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #8A2BE2;");

        this.getChildren().add(contentLabel);
        if (tracksController.fetchAll().size() == 0) {
            this.getChildren().add(getTutorialCard());
            labalHighDiff = 100;
        }

        contentLabel.layoutXProperty().bind(
                this.widthProperty().subtract(contentLabel.widthProperty()).divide(2));
        contentLabel.layoutYProperty().bind(
                this.heightProperty().subtract(contentLabel.heightProperty()).divide(2).subtract(labalHighDiff));
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
        tutorialCard.setStyle("-fx-background-color: #302f2f; -fx-padding: 10px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-border-color: #8A2BE2; -fx-border-width: 1px;");

        Label tutorialLabel = new Label("Comece adicionando músicas! :D");
        tutorialLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff;");
        tutorialCard.getChildren().add(tutorialLabel);

        Label tutorialContentLabel = new Label("1. Entre em 'Minha Biblioteca'.\n2. Adicione uma pasta.\n3. Clique no disquete para salvar.");
        tutorialContentLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #ffffff;");
        tutorialCard.getChildren().add(tutorialContentLabel);

        //center the tutorial card
        tutorialCard.layoutXProperty().bind(
                this.widthProperty().subtract(tutorialCard.widthProperty()).divide(2));
        tutorialCard.layoutYProperty().bind(
                this.heightProperty().subtract(tutorialCard.heightProperty()).divide(2));

        return tutorialCard;
    }
}
