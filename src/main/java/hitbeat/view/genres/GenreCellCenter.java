package hitbeat.view.genres;

import hitbeat.view.base.widgets.Widget;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GenreCellCenter extends Widget {
    private final String genreName;

    public GenreCellCenter(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public Node build() {
        VBox root = new VBox();
        final String genreName = this.genreName;
        final String subtitle = "Genre";
        final String genreNameStyle = "-fx-font-size: 20px; -fx-font-weight: bold; -fx-fill: white;";
        final String subtitleStyle = "-fx-font-size: 15px; -fx-fill: white;";

        root.getChildren().addAll(
                createText(genreName, genreNameStyle),
                createText(subtitle, subtitleStyle)
        );

        return root;
    }

    private Node createText(String text, String style) {
        return new Text() {{
            setText(text);
            setStyle(style);
        }};
    }
    
}
