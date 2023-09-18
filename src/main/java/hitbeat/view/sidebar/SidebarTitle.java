package hitbeat.view.sidebar;

import hitbeat.view.base.widgets.Widget;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class SidebarTitle extends Widget {

    private final String title;

    public SidebarTitle(String title) {
        this.title = title;
    }

    @Override
    public Node build() {
        BorderPane sidebarTitle = new BorderPane();
        sidebarTitle.setCenter(createTitleText());
        sidebarTitle.setPadding(new Insets(0, 0, 0, 10));
        return sidebarTitle;
    }    

    private Text createTitleText() {
        Text text = new Text(title);
        text.getStyleClass().add("sidebar-title");
        text.setWrappingWidth(200);
        return text;
    }
}