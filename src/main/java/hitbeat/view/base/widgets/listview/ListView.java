package hitbeat.view.base.widgets.listview;

import hitbeat.view.Layout;
import javafx.collections.ObservableList;

public class ListView<T> extends javafx.scene.control.ListView<T> {
    @FunctionalInterface
    public interface CellBuilder<T> {
        BaseCell<T> build(T item);
    }

    private CellBuilder<T> cellBuilder;

    public ListView(ObservableList<T> items, CellBuilder<T> cellBuilder) {
        this.cellBuilder = cellBuilder;
        setItems(items);
        init();
    }

    public ListView(CellBuilder<T> cellBuilder) {
        this.cellBuilder = cellBuilder;
        init();
    }

    public ListView(ObservableList<T> items) {
        setItems(items);
        init();
    }

    public ListView() {
        init();
    }

    private void init() {
        this.getStylesheets().add("hitbeat/css/listview/listview.css");

        this.setCellFactory(item -> {
            return new ListCell();
        });


        this.setPadding(new javafx.geometry.Insets(0, 8, 0, 8));
        
    }

    class ListCell extends javafx.scene.control.ListCell<T> {
        private final BaseCell<T> cell;

        public ListCell() {
            cell = cellBuilder.build(null);
        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                resetCell();
            } else {
                updateCellWithItem(item);
            }
        }

        private void resetCell() {
            if (cell != null)
                cell.prefWidthProperty().unbind();
            setText(null);
            setGraphic(null);
        }

        private void updateCellWithItem(T item) {
            cell.updateItem(item);
            cell.prefWidthProperty().bind(Layout.getInstance().getContentWidth().subtract(58));
            cell.getStyleClass().add("my-cell");

            setGraphic(cell);
        }
    }
}
