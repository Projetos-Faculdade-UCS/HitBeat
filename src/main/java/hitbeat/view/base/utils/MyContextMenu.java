package hitbeat.view.base.utils;

import javafx.scene.control.ContextMenu;

public class MyContextMenu extends ContextMenu {

    public MyContextMenu() {
        super();
        initUI();
    }

    private void initUI() {
        this.setStyle("-fx-background-color: #302f2f !important; -fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold; -fx-border-radius: 20px;");
    }
}
