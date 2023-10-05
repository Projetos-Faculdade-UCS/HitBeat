package hitbeat.controller.library;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hitbeat.dao.GenreDAO;
import hitbeat.dao.TrackDAO;
import hitbeat.util.CustomMP3File;
import hitbeat.view.library.LibraryPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.DragEvent;
import javafx.stage.DirectoryChooser;

public class LibraryController {
    private static final String DIRECTORY_TITLE = "Select a Folder";
    private GenreDAO genreDAO;
    private TrackDAO trackDAO;
    private LibraryDatabaseManager libraryDatabaseManager;
    private ObservableList<CustomMP3File> files;
    private LibraryPage libraryPage;

    public LibraryController(LibraryPage libraryPage) {
        this.files = FXCollections.observableArrayList(new ArrayList<>());
        this.libraryPage = libraryPage;
        this.genreDAO = new GenreDAO();
        this.trackDAO = new TrackDAO();
        this.libraryDatabaseManager = new LibraryDatabaseManager(genreDAO, trackDAO);
    }

    /**
     * Abre o seletor de arquivos, permite ao usuário selecionar uma pasta e chama o
     * callback
     * com uma List<CustomMP3File> representando os arquivos MP3 na pasta
     * selecionada.
     *
     * @param callback - Callback para processar os arquivos MP3 selecionados
     */
    public void addFolderToLibrary() {
        Date date = new Date(System.currentTimeMillis());
        File selectedFolder = selectFolder();
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println("Time to select folder: " + (date2.getTime() - date.getTime()) + "ms");
        date = new Date(System.currentTimeMillis());

        addFolderToLibrary(selectedFolder);
    }

    private void addFolderToLibrary(File folder) {
        if (folder != null) {
            Date date = new Date(System.currentTimeMillis());
            List<CustomMP3File> files = getMP3FilesFromFolder(folder);
            
            this.files.addAll(FXCollections.observableArrayList(files));

            Date date2 = new Date(System.currentTimeMillis());
            System.out.println("Time to get mp3 files: " + (date2.getTime() - date.getTime()) + "ms");

            date = new Date(System.currentTimeMillis());
            libraryPage.setFilesFromFolder(this.files);
            date2 = new Date(System.currentTimeMillis());
            System.out.println("Time to set files: " + (date2.getTime() - date.getTime()) + "ms");

            System.out.println("Selected folder: " + folder.getAbsolutePath());
        } else {
            System.out.println("No folder selected");
        }
    }

    /**
     * Apresenta um seletor de diretórios ao usuário e retorna a pasta selecionada.
     *
     * @return Pasta selecionada ou null se nenhuma pasta for selecionada
     */
    private File selectFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(DIRECTORY_TITLE);

        return directoryChooser.showDialog(null);
    }

    /**
     * Recupera arquivos MP3 da pasta fornecida.
     *
     * @param folder - Pasta para buscar arquivos MP3
     * @return Lista de arquivos MP3 envolvidos em objetos CustomMP3File
     */
    private List<CustomMP3File> getMP3FilesFromFolder(File folder) {
        if (folder == null || !folder.isDirectory()) {
            throw new IllegalArgumentException("The provided File object is not a directory or is null.");
        }

        Path dirPath = folder.toPath();
        List<CustomMP3File> mp3Files = new ArrayList<>();

        try {
            Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().toLowerCase().endsWith(".mp3")) {
                        CustomMP3File customMP3File = createCustomMP3File(file.toFile());
                        if (customMP3File != null) {
                            mp3Files.add(customMP3File);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            mp3Files.clear();
        }

        return mp3Files;
    }

    /**
     * Cria um objeto CustomMP3File a partir do arquivo fornecido.
     *
     * @param file - Arquivo para ser envolvido em um objeto CustomMP3File
     * @return Objeto CustomMP3File ou null em caso de erro
     */
    private CustomMP3File createCustomMP3File(File file) {
        try {
            return new CustomMP3File(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveToDatabase() {
        if (files == null || files.isEmpty()) {
            System.out.println("No files to save");
            return;
        }
        libraryDatabaseManager.saveMultipleCustomMP3FilesToDatabase(files);
        files.clear();
    }

    public ObservableList<CustomMP3File> getFiles() {
        return files;
    }

    public void dragEvent(DragEvent event) {
        boolean success = false;
        if (event.getDragboard().hasFiles()) {
            success = true;
            event.getDragboard().getFiles().forEach(file -> {
                addFolderToLibrary(file);
            });
        }
        event.setDropCompleted(success);
        event.consume();
    }
}
