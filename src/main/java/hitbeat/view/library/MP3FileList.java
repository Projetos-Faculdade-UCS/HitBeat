package hitbeat.view.library;

import hitbeat.controller.library.LibraryController;
import hitbeat.util.CustomMP3File;
import hitbeat.view.base.widgets.listview.ListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;

public class MP3FileList extends MFXScrollPane {
    private ObservableList<CustomMP3File> files;
    private LibraryController controller;

    public MP3FileList(LibraryController controller) {
        super();
        this.controller = controller;
        files = this.controller.getFiles();

        ListView<CustomMP3File> listView = new ListView<>(files, genre -> {
            return new SongEditRow(genre);
        });
        
        this.getStyleClass().add("transparent");
        
        this.setContent(listView);

        // grow this pane to fill the parent
        this.setFitToWidth(true);
        this.setFitToHeight(true);
    }

    public void setFiles(ObservableList<CustomMP3File> files) {
        if (this.files == null) {
            this.files = files;
            return;
        }
        this.files.filtered(file -> !files.contains(file)).forEach(file -> {
            this.files.remove(file);
        });

        this.files.addAll(files.filtered(file -> !this.files.contains(file)));
    }
}

