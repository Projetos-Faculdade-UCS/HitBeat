package hitbeat.view.base.widgets;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

/*
 * Botão que herda do materialfx MFXButton, mas que coloca um
 * estilo especifico da aplicação.
 */
public class RoundedButton extends MFXButton {

    public void setUp() {
        this.getStylesheets().add(
                getClass().getResource("/hitbeat/css/index.css").toExternalForm());

        this.getStyleClass().addAll("min-button");

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(this.widthProperty());
        clip.heightProperty().bind(this.heightProperty());
        clip.setArcHeight(100);
        clip.setArcWidth(100);
        this.setClip(clip);
    }

    public RoundedButton() {
        super();
        setUp();
    }

    public RoundedButton(String text) {
        super(text);
        setUp();
    }

    public RoundedButton(String text, double prefWidth, double prefHeight) {
        super(text, prefWidth, prefHeight);
        setUp();
    }

    public RoundedButton(String text, Node graphic) {
        super(text, graphic);
        setUp();
    }

}
