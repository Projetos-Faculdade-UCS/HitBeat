package hitbeat.view.base.widgets.listview;

import javafx.collections.ObservableList;

public class ListView<T> extends javafx.scene.control.ListView<T> {
    public ListView(ObservableList<T> items) {
        setItems(items);
        init();
    }

    private void init() {
        this.setId("custList");
        this.getStylesheets().add("hitbeat/css/listview/listview.css");
        this.setPrefWidth(Integer.MAX_VALUE);
        this.setPrefHeight(Integer.MAX_VALUE);
    }

}
