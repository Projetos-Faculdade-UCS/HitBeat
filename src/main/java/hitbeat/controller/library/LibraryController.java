package hitbeat.controller.library;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import hitbeat.dao.GenreDAO;
import hitbeat.dao.TrackDAO;
import hitbeat.util.CustomMP3File;
import javafx.stage.DirectoryChooser;

public class LibraryController {
    private static final String DIRECTORY_TITLE = "Select a Folder";
    private GenreDAO genreDAO;
    private TrackDAO trackDAO;
    private LibraryDatabaseManager libraryDatabaseManager;
    private List<CustomMP3File> files;

    public LibraryController() {
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
        File selectedFolder = selectFolder();

        if (selectedFolder != null) {
            files = getMP3FilesFromFolder(selectedFolder);

            System.out.println("Selected folder: " + selectedFolder.getAbsolutePath());
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

        File[] filesInDirectory = folder.listFiles();

        if (filesInDirectory == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(filesInDirectory)
                .filter(file -> file.getName().toLowerCase().endsWith(".mp3"))
                .map(this::createCustomMP3File)
                .filter(file -> file != null)
                .collect(Collectors.toList());
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

    public List<CustomMP3File> getFiles() {
        return files;
    }
}
