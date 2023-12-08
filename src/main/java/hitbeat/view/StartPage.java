package hitbeat.view;

import java.util.Map;

import hitbeat.controller.tracks.TracksController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartPage extends Pane implements BaseView {

    private Label contentLabel;
    private int labalHighDiff = 0;
    private VBox tutorialCard;
    private TracksController tracksController = new TracksController();

    public StartPage() {
        VBox content = new VBox();
        content.setAlignment(javafx.geometry.Pos.CENTER);
        content.setSpacing(10);

        content.getStyleClass().add("content");
        

        contentLabel = new Label("Bem vindo ao HitBeat!");
        contentLabel.getStyleClass().add("welcome-text-label");
        this.getChildren().add(content);
        content.getChildren().addAll(getTitle(),getFogueira());

        if (tracksController.fetchAll().size() == 0) {
            content.getChildren().add(getTutorialCard());
            labalHighDiff = 100;
        }

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

        return tutorialCard;
    }

    public ImageView getFogueira(){
        Image fogueiraImg = new Image("/hitbeat/gifs/bonfire8.gif");
        ImageView fogueira = new ImageView(fogueiraImg);
        fogueira.setFitHeight(200);
        fogueira.setFitWidth(200);
        fogueira.setPreserveRatio(true);
        //center de fogueira
        fogueira.setLayoutX(0);

        return fogueira;
    }

    public ImageView getTitle(){
        Image titleImg = new Image("/hitbeat/images/hitbeat-title.png");
        ImageView title = new ImageView(titleImg);
        title.setFitHeight(150);
        title.setPreserveRatio(true);
        //center de title
        title.setLayoutX(0);
        return title;
    }
}
