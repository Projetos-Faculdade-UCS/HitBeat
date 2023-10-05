package hitbeat.view.base.widgets;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.Node;

/*
 * Botão que herda do materialfx MFXButton, mas que coloca um
 * estilo especifico da aplicação.
 */
public class MinButton extends MFXButton {

    public void setUp() {
        this.getStylesheets().add(
                getClass().getResource("/hitbeat/css/index.css").toExternalForm());

        this.getStyleClass().addAll("min-button");
    }

    public MinButton() {
        super();
        setUp();
    }

    public MinButton(String text) {
        super(text);
        setUp();
    }

    public MinButton(String text, double prefWidth, double prefHeight) {
        super(text, prefWidth, prefHeight);
        setUp();
    }

    public MinButton(String text, Node graphic) {
        super(text, graphic);
        setUp();
    }

}
