package hitbeat.controller.library;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.farng.mp3.MP3File;

import hitbeat.view.library.LibraryPage;
import javafx.stage.DirectoryChooser;

public class LibraryController {
    private LibraryPage view;

    // open the file chooser
    // add the selected folder to the library
    // calls callback with a List<File>
    public void addFolderToLibrary(Consumer<List<File>> callback) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Folder");

        // Show the DirectoryChooser and get the selected folder
        File selectedFolder = directoryChooser.showDialog(null);

        if (selectedFolder != null) {
            // Now, you have the selected folder. You can process it further or add it to
            // your library.
            String folderPath = selectedFolder.getAbsolutePath();

            List<File> files = getFilesFromFolder(selectedFolder);
            
            callback.accept(files);

            // For now, let's just print the selected folder path
            System.out.println("Selected folder: " + folderPath);
        } else {
            System.out.println("No folder selected");
        }
    }

    List<File> getFilesFromFolder(File folder) {
        if (folder == null || !folder.isDirectory()) {
            throw new IllegalArgumentException("The provided File object is not a directory or is null.");
        }

        getMP3FilesFromFolder(folder);

        return Arrays.stream(folder.listFiles())
                .filter(file -> file.isFile() && file.getName().endsWith(".mp3"))
                .collect(Collectors.toList());
    }

    List<MP3File> getMP3FilesFromFolder(File folder) {
        if (folder == null || !folder.isDirectory()) {
            throw new IllegalArgumentException("The provided File object is not a directory or is null.");
        }

        return Arrays.stream(folder.listFiles())
                .filter(file -> file.isFile() && file.getName().endsWith(".mp3"))
                .map(file -> {
                    try {
                        MP3File mp3File = new MP3File(file);
                        String title = mp3File.getID3v2Tag().getSongTitle();
                        System.out.println(title);
                        return mp3File;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .collect(Collectors.toList());
    }
}
