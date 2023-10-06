package hitbeat.view.sidebar;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class SidebarItem extends MFXButton {
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
        this.setMaxWidth(Integer.MAX_VALUE);
        this.getStyleClass().add("sidebar-button");
        this.setOnAction(e -> onClick.run());
        HBox.setHgrow(this, Priority.ALWAYS);
    }
}