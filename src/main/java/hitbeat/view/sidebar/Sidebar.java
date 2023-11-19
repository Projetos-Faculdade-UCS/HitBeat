package hitbeat.view.sidebar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {

    private final String title;
    private final Collection<SidebarTopic> topics = new ArrayList<>();
    private VBox topicsSidebar;
    private HBox sidebarHeader;

    public Sidebar(String title, SidebarTopic... topics) {
        super(10);
        this.getStylesheets().add("hitbeat/css/sidebar/sidebar.css");
        this.title = title;
        Collections.addAll(this.topics, topics);
        this.getStyleClass().add("sidebar");

        topicsSidebar = new VBox();
        sidebarHeader = new HBox();
        addTopicsToSidebar(topicsSidebar);

        sidebarHeader.getChildren().add( new SidebarTitle(this.title) );
        this.getChildren().addAll(sidebarHeader, topicsSidebar);
    
        VBox.setVgrow(topics[0], Priority.ALWAYS);
        VBox.setVgrow(this.topicsSidebar, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);

        this.setFillWidth(true);
    }

    public Sidebar(String title, Node logo, SidebarTopic... topics) {
        this(title, topics);
        sidebarHeader.getChildren().add(0, logo);
    }

    private void addTopicsToSidebar(VBox sidebar) {
        sidebar.getChildren().addAll(topics);
    }
}
