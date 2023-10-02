package hitbeat.controller.library;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import hitbeat.dao.GenreDAO;
import hitbeat.dao.TrackDAO;
import hitbeat.model.Genre;
import hitbeat.model.Track;
import hitbeat.util.CustomMP3File;

public class LibraryDatabaseManager {

    private GenreDAO genreDAO;
    private TrackDAO trackDAO;

    public LibraryDatabaseManager(GenreDAO genreDAO, TrackDAO trackDAO) {
        this.genreDAO = genreDAO;
        this.trackDAO = trackDAO;
    }

    public void saveMultipleCustomMP3FilesToDatabase(List<CustomMP3File> customMP3Files) {
        List<Genre> genres = customMP3Files.stream()
                .map(CustomMP3File::getGenre)
                .distinct()
                .map(genreName -> {
                    Genre genre = new Genre();
                    genre.setName(genreName);
                    return genre;
                })
                .collect(Collectors.toList());

        genreDAO.saveAll(genres);

        List<Track> tracks = customMP3Files.stream()
                .map(customMP3File -> {
                    Track track = new Track();
                    track.setName(customMP3File.getTitle());
                    track.setGenre(genres.stream()
                            .filter(genre -> genre.getName().equals(customMP3File.getGenre()))
                            .findFirst()
                            .orElse(null));
                    track.setFilePath(customMP3File.getFilePath());
                    track.setCreationDate(new Date());
                    return track;
                })
                .collect(Collectors.toList());

        trackDAO.saveAll(tracks);
    }

    public void saveCustomMP3FileToDatabase(CustomMP3File customMP3File) {
        // Extract genre from CustomMP3File
        String genreName = customMP3File.getGenre();

        // Check if genre exists
        Genre genre = genreDAO.findByName(genreName);
        if (genre == null) {
            genre = new Genre();
            genre.setName(genreName);
            genreDAO.save(genre); // Save new genre to database
        }

        // Create a new track
        Track track = new Track();
        track.setName(customMP3File.getTitle());
        track.setGenre(genre); // Set the genre reference
        track.setFilePath(customMP3File.getFilePath());
        track.setCreationDate(new Date());

        trackDAO.save(track); // Save track to database
    }
}
