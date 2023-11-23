package hitbeat.view.base.utils;

import javafx.scene.control.Menu;

public class MyMenu extends Menu{

    public MyMenu(String text) {
        super(text);
        initUI();
    }

    private void initUI() {
        this.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold; -fx-background-color: #302f2f");
    }
}
