package hitbeat.view.library;

import java.util.List;

import hitbeat.controller.library.LibraryController;
import hitbeat.util.CustomMP3File;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LibraryPage extends VBox {
    LibraryController controller = new LibraryController();
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

        this.getChildren().add(filesBox);
    }

    private void setFilesFromFolder(List<CustomMP3File> files) {
        filesBox.getChildren().clear();

        if (files == null || files.isEmpty()) {
            Text text = new Text("No .mp3 files found.");
            filesBox.getChildren().add(text);
            return;
        }

        for (CustomMP3File file : files) {
            HBox fileBox = new HBox();
            Text text = new Text(file.getTitle() + " -> " + file.getAlbum() + " -> " + file.getArtist() + " -> "
                    + file.getGenre());
            text.setId("text");
            text.setStyle("-fx-font-size: 20px; -fx-text-fill: white !important;");
            fileBox.getChildren().add(text);
            filesBox.getChildren().add(fileBox);
        }
    }
}
