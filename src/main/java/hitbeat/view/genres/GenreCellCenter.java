package hitbeat.view.genres;

import javafx.scene.text.Text;

public class GenreCellCenter extends Text {

    public GenreCellCenter(String genreName) {
        this.setText(genreName);
        this.setId("genreCenterName");
        this.setY(50);
        this.prefHeight(50);

    }

}
