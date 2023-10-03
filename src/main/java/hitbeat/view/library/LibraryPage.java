package hitbeat.view.library;

import java.util.List;

import hitbeat.controller.library.LibraryController;
import hitbeat.util.CustomMP3File;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LibraryPage extends VBox {
    LibraryController controller = new LibraryController();
    VBox filesBox;

    public LibraryPage() {
        super();
        this.getStyleClass().add("library-page");

        MFXButton button = new MFXButton("Add Folder to Library");
        button.getStyleClass().add("button-raised");
        button.setOnAction(e -> {
            controller.addFolderToLibrary();
            setFilesFromFolder(controller.getFiles());
        });
        this.getChildren().add(button);

        MFXScrollPane scrollPane = new MFXScrollPane();
        filesBox = new VBox();
        filesBox.setSpacing(10);
        scrollPane.setContent(filesBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);

        scrollPane.getStyleClass().add("scroll-pane");
        this.getChildren().add(scrollPane);

        scrollPane.getStyleClass().add("scroll-pane");
        filesBox.getStyleClass().add("files-box");

        // save button (floats on the bottom right)
        MFXButton saveButton = new MFXButton("Save");
        saveButton.getStyleClass().add("button-raised");
        saveButton.setOnAction(e -> {
            controller.saveToDatabase();
            setFilesFromFolder(controller.getFiles());
        });
        saveButton.getStyleClass().add("save-button");
        saveButton.getStyleClass().add("button-raised");

        this.getChildren().add(saveButton);

        this.getStylesheets().add(getClass().getResource("/hitbeat/css/library/library.css").toExternalForm());
    }

    private void setFilesFromFolder(List<CustomMP3File> files) {
        filesBox.getChildren().clear();

        if (files == null || files.isEmpty()) {
            Text text = new Text("No .mp3 files found.");
            filesBox.getChildren().add(text);
            return;
        }

        for (CustomMP3File file : files) {
            SongEditRow songEditRow = new SongEditRow(file);
            filesBox.getChildren().add(songEditRow);
        }

    }

}
