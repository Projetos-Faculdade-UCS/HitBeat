package hitbeat.controller.library;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hitbeat.dao.AlbumDAO;
import hitbeat.dao.ArtistDAO;
import hitbeat.dao.GenreDAO;
import hitbeat.dao.TrackDAO;
import hitbeat.model.Album;
import hitbeat.model.Artist;
import hitbeat.model.Genre;
import hitbeat.model.Track;
import hitbeat.util.CustomMP3File;

public class LibraryDatabaseManager {

    private GenreDAO genreDAO;
    private TrackDAO trackDAO;
    private ArtistDAO artistDAO;
    private AlbumDAO albumDAO;

    public LibraryDatabaseManager(GenreDAO genreDAO, TrackDAO trackDAO) {
        this.genreDAO = genreDAO;
        this.trackDAO = trackDAO;
        this.artistDAO = new ArtistDAO();
        this.albumDAO = new AlbumDAO();
    }

    private List<Genre> saveGenres(List<CustomMP3File> customMP3Files) {
        // Extract distinct genres from the MP3 files
        List<String> genreNames = customMP3Files.stream()
                .map(CustomMP3File::getGenre)
                .distinct()
                .collect(Collectors.toList());

        // Fetch existing genres from the database
        List<Genre> existingGenres = genreDAO.getGenresByNames(genreNames);

        // Determine which genre names are not yet in the database
        List<String> newGenreNames = genreNames.stream()
                .filter(name -> existingGenres.stream().noneMatch(genre -> genre.getName().equals(name)))
                .collect(Collectors.toList());

        // Create new Genre objects for those names and save them
        List<Genre> newGenres = newGenreNames.stream()
                .map(name -> {
                    Genre genre = new Genre();
                    genre.setName(name);
                    return genre;
                })
                .collect(Collectors.toList());
        genreDAO.bulkCreateOrUpdate(newGenres, "name");

        // Merge both lists of genres for track association
        return Stream.concat(existingGenres.stream(), newGenres.stream()).collect(Collectors.toList());
    }

    private List<Artist> saveArtists(List<CustomMP3File> customMP3Files) {
        // Extract distinct artists from the MP3 files
        List<String> artistNames = customMP3Files.stream()
                .map(CustomMP3File::getArtist)
                .distinct()
                .collect(Collectors.toList());

        // Fetch existing artists from the database
        List<Artist> existingArtists = artistDAO.findByName(artistNames);

        // Determine which artist names are not yet in the database
        List<String> newArtistNames = artistNames.stream()
                .filter(name -> existingArtists.stream().noneMatch(artist -> artist.getName().equals(name)))
                .collect(Collectors.toList());

        // Create new Artist objects for those names and save them
        List<Artist> newArtists = newArtistNames.stream()
                .map(name -> {
                    Artist artist = new Artist();
                    artist.setName(name);
                    return artist;
                })
                .collect(Collectors.toList());
        artistDAO.bulkCreateOrUpdate(newArtists, "name");

        // Merge both lists of artists for track association
        return Stream.concat(existingArtists.stream(), newArtists.stream()).collect(Collectors.toList());
    }

    public List<Album> saveAlbums(List<CustomMP3File> customMP3Files) {
        // Extract distinct albums from the MP3 files
        List<String> albumNames = customMP3Files.stream()
                .map(CustomMP3File::getAlbum)
                .distinct()
                .collect(Collectors.toList());

        // Fetch existing albums from the database
        List<Album> existingAlbums = albumDAO.findByName(albumNames);

        // Determine which album names are not yet in the database
        List<String> newAlbumNames = albumNames.stream()
                .filter(name -> existingAlbums.stream().noneMatch(album -> album.getName().equals(name)))
                .collect(Collectors.toList());

        // Create new Album objects for those names and save them
        List<Album> newAlbums = newAlbumNames.stream()
                .map(name -> {
                        CustomMP3File customMP3File = customMP3Files.stream()
                                .filter(mp3File -> mp3File.getAlbum().equals(name))
                                .findFirst()
                                .orElse(null);
                        Album album = new Album();
                        album.setName(name);
                        LocalDate localDate = LocalDate.of(Integer.parseInt(customMP3File.getYear()), 1, 1);
                        Date date = java.sql.Date.valueOf(localDate);
                        
                        album.setLaunchDate(date);
                        album.setCover(customMP3File.getImage());

                    return album;
                })
                .collect(Collectors.toList());
        albumDAO.bulkCreateOrUpdate(newAlbums, "name");

        // Merge both lists of albums for track association
        return Stream.concat(existingAlbums.stream(), newAlbums.stream()).collect(Collectors.toList());
    }

    public void saveMultipleCustomMP3FilesToDatabase(List<CustomMP3File> customMP3Files) {
        List<Genre> allGenres = saveGenres(customMP3Files);

        // Create and save artists
        List<Artist> artists = saveArtists(customMP3Files);

        List<Album> albums = saveAlbums(customMP3Files);

        // Create and save tracks with associated genres
        List<Track> tracks = customMP3Files.stream()
                .map(customMP3File -> {
                    Track track = new Track();
                    track.setName(customMP3File.getTitle());
                    track.setGenre(allGenres.stream()
                            .filter(genre -> genre.getName().equals(customMP3File.getGenre()))
                            .findFirst()
                            .orElse(null));
                    track.setFilePath(customMP3File.getFilenameAsUri());
                    track.setCreationDate(new Date());
                    track.setArtist(artists.stream()
                            .filter(artist -> artist.getName().equals(customMP3File.getArtist()))
                            .findFirst()
                            .orElse(null));
                    track.setAlbum(albums.stream()
                            .filter(album -> album.getName().equals(customMP3File.getAlbum()))
                            .findFirst()
                            .orElse(null));
                    return track;
                })
                .collect(Collectors.toList());
        trackDAO.bulkCreateOrUpdate(tracks, "filePath");
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
