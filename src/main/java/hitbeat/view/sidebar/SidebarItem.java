package hitbeat.view.sidebar;

import hitbeat.styles.Styles;
import hitbeat.view.base.widgets.Widget;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.Node;

public class SidebarItem extends Widget {

    private static final double BUTTON_MIN_WIDTH = 200;

    private final String text;
    private final Node icon;
    private final Runnable onClick;

    public SidebarItem(String text, Node icon, Runnable onClick) {
        this.text = text;
        this.icon = icon;
        this.onClick = onClick;
    }

    @Override
    public Node build() {
        return setupButton();
    }

    private MFXButton setupButton() {
        MFXButton button = new MFXButton(text, icon);
        button.setMinWidth(BUTTON_MIN_WIDTH);
        button.setStyle(Styles.SIDEBAR_BUTTONS);
        button.setOnAction(e -> onClick.run());
        return button;
    }
}
