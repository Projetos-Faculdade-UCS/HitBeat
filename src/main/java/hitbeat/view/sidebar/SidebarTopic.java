package hitbeat.view.sidebar;

import java.util.ArrayList;
import java.util.Collections;

import hitbeat.view.base.widgets.Widget;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SidebarTopic extends Widget {
    private String title;
    private ArrayList<SidebarItem> items = new ArrayList<>();

    public SidebarTopic(String title, SidebarItem... items) {
        this.title = title;
        Collections.addAll(this.items, items);
    }

    @Override
    public Node build() {
        VBox sidebarTopic = new VBox(10);
        
        sidebarTopic.getChildren().add(createTitlePane());
        items.forEach(item -> sidebarTopic.getChildren().add(item.build()));

        return sidebarTopic;
    }

    private BorderPane createTitlePane() {
        BorderPane textPane = new BorderPane();
        textPane.setCenter(createTitleText());
        textPane.setPadding(new Insets(0, 0, 0, 10));
        return textPane;
    }

    private Text createTitleText() {
        Text text = new Text(title);
        text.getStyleClass().add("sidebar-topic");
        text.setWrappingWidth(200);
        return text;
    }
}
