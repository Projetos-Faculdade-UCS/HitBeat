package hitbeat.view;

import java.util.Map;

import hitbeat.controller.tracks.TracksController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class StartPage extends Pane implements BaseView {

    private Label contentLabel;
    private VBox tutorialCard;
    private TracksController tracksController = new TracksController();

    public StartPage() {
        VBox content = new VBox();
        content.setAlignment(javafx.geometry.Pos.CENTER);
        content.setSpacing(20);

        content.getStyleClass().add("content");
        
        contentLabel = new Label("Seja bem vindo!");
        contentLabel.getStyleClass().add("welcome-text-label");
        this.getChildren().add(content);

        HBox fogueiraBox = new HBox();
        fogueiraBox.setAlignment(javafx.geometry.Pos.CENTER);
        fogueiraBox.getChildren().addAll(getFogueira(), contentLabel);
        fogueiraBox.setSpacing(10);
        content.getChildren().addAll(getTitle(),fogueiraBox);

        if (tracksController.fetchAll().size() == 0) {
            content.getChildren().add(getTutorialCard());
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

    public ImageView getFogueira(){
        Image fogueiraImg = new Image("/hitbeat/gifs/bonfire8.gif");
        ImageView fogueira = new ImageView(fogueiraImg);
        fogueira.setFitHeight(80);
        fogueira.setPreserveRatio(true);
        //center de fogueira
        fogueira.setLayoutX(0);

        return fogueira;
    }

    public ImageView getTitle(){
        Image titleImg = new Image("/hitbeat/images/hitbeat-title2.png");
        ImageView title = new ImageView(titleImg);
        // bind title width to window width
        title.fitWidthProperty().bind(this.widthProperty());
        title.setPreserveRatio(true);
        //center de title
        title.setLayoutX(0);
        return title;
    }
}
