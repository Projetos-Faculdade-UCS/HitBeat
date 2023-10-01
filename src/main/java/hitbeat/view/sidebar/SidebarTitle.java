package hitbeat.view.sidebar;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class SidebarTitle extends BorderPane {

    private final String title;

    public SidebarTitle(String title) {
        this.title = title;
        this.setCenter(createTitleText());
        this.setPadding(new Insets(0, 0, 0, 10));
    }

    private Text createTitleText() {
        Text text = new Text(title);
        text.getStyleClass().add("sidebar-title");
        text.setWrappingWidth(200);
        return text;
    }
}