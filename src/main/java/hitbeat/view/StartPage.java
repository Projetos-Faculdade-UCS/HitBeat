package hitbeat.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class StartPage extends Pane implements BaseView{

    private Label contentLabel;

    public StartPage() {
        contentLabel = new Label("Welcome to the HitBeat!");
        contentLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #2195f3;");

        this.getChildren().add(contentLabel);

        contentLabel.layoutXProperty().bind(
                this.widthProperty().subtract(contentLabel.widthProperty()).divide(2));
        contentLabel.layoutYProperty().bind(
                this.heightProperty().subtract(contentLabel.heightProperty()).divide(2));
    }

    public void setContentText(String text) {
        contentLabel.setText(text);
    }

    @Override
    public Object getData() {
        return null;
    }
}
