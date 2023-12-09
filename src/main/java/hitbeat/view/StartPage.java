package hitbeat.view;

import java.util.Map;

import hitbeat.controller.tracks.TracksController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class StartPage extends Pane implements BaseView {
    private VBox tutorialCard;
    private TracksController tracksController = new TracksController();

    public StartPage() {
        VBox content = new VBox();
        content.prefHeightProperty().bind(this.heightProperty());
        content.setSpacing(20);
        content.getStyleClass().add("content");
        this.getChildren().add(content);
        
        Region space1 = new Region();
        space1.prefHeightProperty().bind(this.heightProperty());

        content.getChildren().addAll(getTitle(), space1, getFogueira());
        if (tracksController.fetchAll().size() == 0) {
            content.getChildren().add(getTutorialCard());
        }
        Region space2 = new Region();
        space2.prefHeightProperty().bind(this.heightProperty());
        content.getChildren().addAll(space2, getWaves());

        // add stylesheets
        this.getStylesheets().add("/hitbeat/css/start-page/start-page.css");
    }

    @Override
    public Map<String, Object> getData() {
        return null;
    }

    public HBox getTutorialCard() {
        HBox tutorialCardBox = new HBox();
        tutorialCardBox.setAlignment(javafx.geometry.Pos.CENTER);
        
        tutorialCard = new VBox();
        tutorialCard.setSpacing(10);
        tutorialCard.getStyleClass().add("tutorial-card");

        Label tutorialLabel = new Label("Comece adicionando músicas! :D");
        tutorialLabel.getStyleClass().addAll("tutorial-label", "main-text");

        Label tutorialContentLabel = new Label(
                "1. Entre em 'Minha Biblioteca'.\n2. Adicione uma pasta.\n3. Clique no disquete para salvar.");
        tutorialContentLabel.getStyleClass().addAll("tutorial-content-label", "main-text");
        tutorialCard.getChildren().addAll(tutorialLabel, tutorialContentLabel);
        
        tutorialCardBox.getChildren().add(tutorialCard);
        return tutorialCardBox;
    }

    public HBox getFogueira(){
        Label contentLabel = new Label("Seja bem vindo!");
        contentLabel.getStyleClass().add("welcome-text-label");
        
        Image fogueiraImg = new Image("/hitbeat/gifs/bonfire8.gif");
        ImageView fogueira = new ImageView(fogueiraImg);
        fogueira.setFitHeight(80);
        fogueira.setPreserveRatio(true);

        HBox fogueiraBox = new HBox();
        fogueiraBox.setSpacing(10);
        fogueiraBox.setAlignment(javafx.geometry.Pos.CENTER);
        fogueiraBox.getChildren().addAll(fogueira, contentLabel);

        return fogueiraBox;
    }

    public HBox getTitle(){
        Image titleImg = new Image("/hitbeat/images/hitbeat-title.png");
        ImageView title = new ImageView(titleImg);
        title.setFitHeight(100);
        title.setPreserveRatio(true);

        HBox titleBox = new HBox();
        titleBox.setSpacing(10);
        titleBox.setAlignment(javafx.geometry.Pos.CENTER);
        titleBox.prefWidthProperty().bind(this.widthProperty());
        titleBox.getChildren().add(title);
        return titleBox;
    }

    public HBox getWaves(){
        Image wavesImg = new Image("/hitbeat/images/song-waves.png");
        ImageView waves = new ImageView(wavesImg);

        //center de waves
        waves.setFitHeight(50);
        waves.setPreserveRatio(true);

        HBox wavesBox = new HBox();
        wavesBox.setSpacing(10);
        wavesBox.setAlignment(javafx.geometry.Pos.CENTER);
        wavesBox.prefWidthProperty().bind(this.widthProperty());
        wavesBox.getChildren().add(waves);
        return wavesBox;
    }
}
