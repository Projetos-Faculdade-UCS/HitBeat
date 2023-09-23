package hitbeat.view.genres;

import hitbeat.view.base.widgets.Widget;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class GenreCellCenter extends Widget {
    private final String genreName;

    public GenreCellCenter(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public Node build() {
        final String genreName = this.genreName;

        Text text = createText(genreName);
        text.setId("genreCenterName");
        text.setY(50);

        text.prefHeight(50);

        return text;
    }

    private Text createText(String text) {
        return new Text() {{
            setText(text);
        }};
    }
    
}
