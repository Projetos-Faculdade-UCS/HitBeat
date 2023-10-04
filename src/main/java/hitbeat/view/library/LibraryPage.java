package hitbeat.view.library;

import hitbeat.controller.library.LibraryController;
import hitbeat.util.CustomMP3File;
import hitbeat.view.Layout;
import hitbeat.view.base.widgets.Margin;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class LibraryPage extends VBox {

    private LibraryController controller;
    private ListView<CustomMP3File> filesBox;

    private MFXScrollPane scrollPane;

    private int deferredLayoutPasses = 0;

    public LibraryPage() {
        super();

        controller = new LibraryController(this);

        initializeStyling();
        configureChildren();

        getStylesheets().add(getClass().getResource("/hitbeat/css/library/library.css").toExternalForm());
    }

    private void initializeStyling() {
        getStyleClass().add("library-page");
    }

    private void configureChildren() {
        addDragAndDrop();
        addFilesBoxToScrollPane();
        addSaveButton();
    }

    private void addDragAndDrop() {
        DragAndDrop dragAndDrop = new DragAndDrop(controller);
        getChildren().add(dragAndDrop);
    }

    private void addFilesBoxToScrollPane() {
        createConfiguredScrollPane();
        getChildren().add(scrollPane);
    }

    private void createConfiguredScrollPane() {
        scrollPane = new MFXScrollPane();

        filesBox = new ListView<>(null);
        filesBox.setCellFactory(param -> new SongEditRowCell());
        filesBox.getStyleClass().add("files-box");

        scrollPane.setContent(filesBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.getStyleClass().add("scroll-pane");

        VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);
    }

    private void addSaveButton() {
        MFXButton saveButton = createSaveButton();
        getChildren().add(saveButton);
    }

    private MFXButton createSaveButton() {
        MFXButton saveButton = new MFXButton("Save");
        saveButton.getStyleClass().addAll("save-button", "button-raised");
        saveButton.setOnAction(e -> {
            controller.saveToDatabase();
            setFilesFromFolder(controller.getFiles());
        });
        return saveButton;
    }

    public void setFilesFromFolder(ObservableList<CustomMP3File> files) {
        filesBox.setItems(files);
    }

    class SongEditRowCell extends ListCell<CustomMP3File> {
        private final SongEditRow songEditRow;

        public SongEditRowCell() {
            songEditRow = new SongEditRow(null);
        }

        @Override
        protected void updateItem(CustomMP3File file, boolean empty) {
            super.updateItem(file, empty);

            if (file == null || empty) {
                resetCell();
            } else {
                updateCellWithFile(file);
            }

            if (deferredLayoutPasses == 0) {
                System.out.println("request layout");
                Platform.runLater(() -> {
                    requestLayout();
                    deferredLayoutPasses++;
                });
            }
        }

        private void resetCell() {
            songEditRow.prefWidthProperty().unbind();
            setText(null);
            setGraphic(null);
            setId("hidden-list-cell");
        }

        private void updateCellWithFile(CustomMP3File file) {
            Margin margin = new Margin(songEditRow, 0, 0, 30, 0);
            songEditRow.updateFile(file);
            songEditRow.prefWidthProperty().bind(Layout.getInstance().getContentWidth().subtract(60));
            setGraphic(margin);
            songEditRow.setId("list-cell");
        }
    }
}
