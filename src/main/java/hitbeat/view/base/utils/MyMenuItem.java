package hitbeat.view.base.utils;

import javafx.scene.control.MenuItem;

public class MyMenuItem extends MenuItem{
    public MyMenuItem(String text){
        super(text);
        initUI();
    }

    private void initUI() {
        this.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");
    }
}
