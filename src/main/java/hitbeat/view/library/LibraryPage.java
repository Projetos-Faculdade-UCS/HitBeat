package hitbeat.view.library;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import hitbeat.controller.library.LibraryController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LibraryPage extends VBox {
    LibraryController controller = new LibraryController();
    List<String> files;
    VBox filesBox;

    public LibraryPage() {
        super();
        MFXButton button = new MFXButton("Add Folder to Library");
        button.getStyleClass().add("button-raised");
        button.setOnAction(e -> {
            controller.addFolderToLibrary((files) -> setFilesFromFolder(files));
        });

        this.getChildren().add(button);

        // show files from folder
        MFXScrollPane scrollPane = new MFXScrollPane();
        filesBox = new VBox();
        scrollPane.setContent(filesBox);

        this.getChildren().add(scrollPane);
    }

    private void setFilesFromFolder(List<File> files) {
        filesBox.getChildren().clear();
        if (files == null || files.isEmpty()) {
            Text text = new Text("No .mp3 files found.");
            filesBox.getChildren().add(text);
            return;
        }
        this.files = files.stream().map(File::getName).collect(Collectors.toList());
        for (String file : this.files) {
            filesBox.getChildren().add(new MFXButton(file));
        }
    }
}
