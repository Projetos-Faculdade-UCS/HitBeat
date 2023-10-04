package hitbeat.view.base.widgets.listview;

import hitbeat.view.Layout;
import hitbeat.view.base.widgets.Margin;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
        this.getStyleClass().add("list-view");
        this.getStyleClass().add("transparent");

        this.setCellFactory(item -> {
            return new ListCell();
        });

        HBox.setHgrow(this, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(this, javafx.scene.layout.Priority.ALWAYS);

    }

    class ListCell extends javafx.scene.control.ListCell<T> {
        private final BaseCell<T> cell;

        public ListCell() {
            cell = cellBuilder.build(null); // Pass null initially, the updateItem will set the correct item.
            cell.prefWidthProperty().bind(Layout.getInstance().getContentWidth().subtract(20));
            this.getStyleClass().add("list-cell");
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
            getStyleClass().add("hidden-list-cell");
        }

        private void updateCellWithItem(T item) {
            cell.updateItem(item);
            cell.prefWidthProperty().bind(Layout.getInstance().getContentWidth().subtract(20));

            Margin margin = new Margin(cell, 0, 0, 8, 0);

            setGraphic(margin);
            setOnMouseClicked(event -> {
                // System.out.println("Clicked on " + genre.getName());
            });
            getStyleClass().remove("hidden-list-cell");
        }
    }

}
