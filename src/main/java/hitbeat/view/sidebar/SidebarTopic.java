package hitbeat.view.sidebar;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SidebarTopic extends VBox {
    private String title;
    private ArrayList<SidebarItem> items = new ArrayList<>();

    public SidebarTopic(String title, SidebarItem... items) {
        super(0);
        this.title = title;
        Collections.addAll(this.items, items);
        this.setFillWidth(true);
        HBox.setHgrow(this, Priority.ALWAYS);

        this.getChildren().add(createTitlePane());
        this.items.forEach(item -> this.getChildren().add(item));
    }

    private BorderPane createTitlePane() {
        BorderPane textPane = new BorderPane();
        textPane.setCenter(createTitleText());
        textPane.setPadding(new Insets(0, 0, 0, 0));
        return textPane;
    }

    private Text createTitleText() {
        Text text = new Text(title);
        text.getStyleClass().add("sidebar-topic");
        text.setWrappingWidth(200);
        return text;
    }
}