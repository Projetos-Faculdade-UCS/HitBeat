package hitbeat.view.base.widgets.listview;

import javafx.scene.layout.Pane;

public abstract class BaseCell<T> extends Pane {
    public BaseCell() {
        super();
    }

    public abstract void updateItem(T item);
}
