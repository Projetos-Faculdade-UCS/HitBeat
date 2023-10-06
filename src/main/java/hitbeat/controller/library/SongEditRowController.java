package hitbeat.controller.library;

import hitbeat.util.CustomMP3File;
import javafx.beans.value.ObservableValue;

public class SongEditRowController {
    private CustomMP3File file;

    public SongEditRowController(CustomMP3File file) {
        this.file = file;
    }

    public CustomMP3File getFile() {
        return file;
    }

    public void titleTextListener(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        file.setTitle(newValue);
    }
    
    public void genreTextListener(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        file.setGenre(newValue);
    }
    
    public void setFile(CustomMP3File file2) {
        this.file = file2;
    }

    public void artistTextListener(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        file.setArtist(newValue);
    }
   
}
