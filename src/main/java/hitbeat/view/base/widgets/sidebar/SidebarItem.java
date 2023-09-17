package hitbeat.view.base.widgets.sidebar;

import hitbeat.styles.Styles;
import hitbeat.view.base.widgets.Widget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.Node;

public class SidebarItem extends Widget {

    private String text;
    private Node icon;
    private Runnable onClick;

    public SidebarItem(String text, Node icon, Runnable onClick) {
        this.text = text;
        this.icon = icon;
        this.onClick = onClick;
    }

    @Override
    public Node build() {
        MFXButton button = new MFXButton(text, icon);
        button.setMinWidth(200);
        button.setStyle(Styles.SIDEBAR_BUTTONS);
        button.setOnAction(e -> onClick.run());
        return button;
    }

}
