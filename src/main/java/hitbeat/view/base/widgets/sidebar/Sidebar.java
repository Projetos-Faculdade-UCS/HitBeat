package hitbeat.view.base.widgets.sidebar;

import java.util.ArrayList;

import hitbeat.view.base.widgets.Widget;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class Sidebar extends Widget {
    private ArrayList<SidebarItem> items = new ArrayList<SidebarItem>();

    public Sidebar(SidebarItem... items) {
        for (SidebarItem item : items) {
            this.items.add(item);
        }
    }

    @Override
    public Node build() {
        VBox sidebar = new VBox(10);

        for (SidebarItem item : items) {
            sidebar.getChildren().add(item.build());
        }

        return sidebar;
    }

}
