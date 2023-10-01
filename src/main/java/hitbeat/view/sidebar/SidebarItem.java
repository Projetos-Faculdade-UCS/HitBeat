package hitbeat.view.sidebar;

import hitbeat.styles.Styles;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.Node;

public class SidebarItem extends MFXButton {

    private static final double BUTTON_MIN_WIDTH = 200;

    private final String text;
    private final Node icon;
    private final Runnable onClick;

    public SidebarItem(String text, Node icon, Runnable onClick) {
        this.text = text;
        this.icon = icon;
        this.onClick = onClick;
        setupButton();
    }

    private void setupButton() {
        this.setGraphic(icon);
        this.setText(text);
        this.setMinWidth(BUTTON_MIN_WIDTH);
        this.setStyle(Styles.SIDEBAR_BUTTONS);
        this.setOnAction(e -> onClick.run());
    }
}