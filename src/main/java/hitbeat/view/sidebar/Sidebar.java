package hitbeat.view.sidebar;

import java.util.ArrayList;
import java.util.Collections;

import hitbeat.view.base.widgets.Widget;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
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
        
        // Wrap the sidebar in a MaterialFX scroll pane
        MFXScrollPane scrollableSidebar = new MFXScrollPane();
        scrollableSidebar.setContent(sidebar);
        scrollableSidebar.setPrefWidth(250);
        // set background color
        scrollableSidebar.setStyle("-fx-background-color: #1e1e1e;");

        return scrollableSidebar;
    }

    private void addTopicsToSidebar(VBox sidebar) {
        topics.forEach(topic -> sidebar.getChildren().add(topic.build()));
    }
}
