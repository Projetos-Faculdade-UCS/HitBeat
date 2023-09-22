package hitbeat.view.base.widgets.listview;

import java.util.function.Function;

import hitbeat.view.base.widgets.Widget;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ListView<T> extends Widget {
    private VBox root;
    private MFXListView<T> listView = new MFXListView<T>();
    private ObservableList<T> items;
    private Function<T, MFXListCell<T>> cellFactory;

    public ListView(ObservableList<T> items) {
        this.items = items;
    }

    public MFXListView<T> getListView() {
        return listView;
    }

    public void setCellFactory(Function<T, MFXListCell<T>> cellFactory) {
        this.cellFactory = cellFactory;
    }

    @Override
    public Node build() {
        root = new VBox();
        listView.setItems(this.items);
        listView.setCellFactory(this.cellFactory);

        initialize();

        root.getChildren().add(listView);

        root.setStyle("-fx-background-color: transparent;");
        VBox.setVgrow(listView, javafx.scene.layout.Priority.ALWAYS);
        root.setPrefHeight(Integer.MAX_VALUE);
        return root;
    }

    private void initialize() {
        listView.setId("custList");
        listView.getStylesheets().add("hitbeat/css/listview/listview.css");
        listView.setPrefWidth(Integer.MAX_VALUE);
        listView.setPrefHeight(Integer.MAX_VALUE);
    }

}
