package hitbeat.view.sidebar;

import java.util.ArrayList;
import java.util.Collections;

import hitbeat.view.base.widgets.Widget;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class Sidebar extends Widget {

    private final String title;
    private final ArrayList<SidebarTopic> topics = new ArrayList<>();

    public Sidebar(String title, SidebarTopic... topics) {
        this.title = title;
        Collections.addAll(this.topics, topics);
    }

    @Override
    public Node build() {
        VBox sidebar = new VBox(10);
        sidebar.getChildren().add(new SidebarTitle(title).build());
        addTopicsToSidebar(sidebar);

        return sidebar;
    }

    private void addTopicsToSidebar(VBox sidebar) {
        topics.forEach(topic -> sidebar.getChildren().add(topic.build()));
    }
}
