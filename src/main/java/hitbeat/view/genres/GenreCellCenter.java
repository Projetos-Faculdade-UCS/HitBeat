package hitbeat.view.genres;

import hitbeat.view.base.widgets.Widget;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GenreCellCenter extends Widget {
    private final String genreName;

    public GenreCellCenter(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public Node build() {
        final String genreName = this.genreName;
        final String genreNameStyle = "-fx-font-size: 20px; -fx-font-weight: bold; -fx-fill: white;";

        Node text = createText(genreName, genreNameStyle);

        VBox.setVgrow(text, javafx.scene.layout.Priority.SOMETIMES);
        HBox.setHgrow(text, javafx.scene.layout.Priority.SOMETIMES);

        return text;
    }

    private Node createText(String text, String style) {
        return new Text() {{
            setText(text);
            setStyle(style);
        }};
    }
    
}
