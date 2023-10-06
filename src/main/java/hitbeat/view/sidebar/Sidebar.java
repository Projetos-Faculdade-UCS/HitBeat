package hitbeat.view.sidebar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {

    private final String title;
    private final Collection<SidebarTopic> topics = new ArrayList<>();
    private VBox topicsSidebar;

    public Sidebar(String title, SidebarTopic... topics) {
        super(10);
        this.getStylesheets().add("hitbeat/css/sidebar/sidebar.css");
        this.title = title;
        Collections.addAll(this.topics, topics);
        this.getStyleClass().add("sidebar");

        topicsSidebar = new VBox();
        this.getChildren().add(new SidebarTitle(this.title));
        addTopicsToSidebar(topicsSidebar);
        this.getChildren().add(topicsSidebar);
        VBox.setVgrow(topics[0], Priority.ALWAYS);
        VBox.setVgrow(this.topicsSidebar, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);

        this.setFillWidth(true);
    }

    private void addTopicsToSidebar(VBox sidebar) {
        sidebar.getChildren().addAll(topics);
    }
}
