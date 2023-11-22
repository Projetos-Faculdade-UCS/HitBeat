package hitbeat.controller;

import hitbeat.model.Playlist;
import hitbeat.view.StartPage;
import hitbeat.view.artists.ArtistsView;
import hitbeat.view.base.widgets.FloatingActionButton;
import hitbeat.view.base.widgets.Miolo;
import hitbeat.view.genres.GenresView;
import hitbeat.view.library.LibraryPage;
import hitbeat.view.playlists.CreatePlaylist;
import hitbeat.view.playlists.DetailPlaylist;
import hitbeat.view.playlists.PlaylistView;
import hitbeat.view.tracks.TracksView;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import javafx.application.Platform;

public class MioloController {
    private BehaviorSubject<MioloUpdated> contentChangedSubject = BehaviorSubject.create();
    private Miolo miolo;

    private MioloController() {
    }

    private static class SingletonHelper {
        private static final MioloController INSTANCE = new MioloController();
    }

    public static MioloController getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void setMiolo(Miolo miolo) {
        this.miolo = miolo;
    }

    public void setFab(FloatingActionButton fab) {
        Platform.runLater(() -> {
            miolo.setFab(fab);
        });
    }

    public void setTitle(String title) {
        Platform.runLater(() -> {
            miolo.setTitle(title);
        });
    }

    public void loadStartPage() {
        StartPage startPage = new StartPage();
        updateContent(new MioloUpdated(startPage, "index", "Início"));
    }

    public void loadTracksView() {
        TracksView tracksView = new TracksView();
        updateContent(new MioloUpdated(tracksView, "tracks", "Todas"));
    }

    public void loadGenresView() {
        GenresView genresView = new GenresView();
        updateContent(new MioloUpdated(genresView, "genres", "Gêneros"));
    }

    public void loadLibraryView() {
        LibraryPage libraryPage = new LibraryPage();
        updateContent(new MioloUpdated(libraryPage, "library", "Minha Biblioteca"));
    }

    public void loadArtistsView() {
        ArtistsView artistsView = new ArtistsView();
        updateContent(new MioloUpdated(artistsView, "artists", "Artistas"));
    }

    public void loadPlaylistsView() {
        PlaylistView playlistsView = new PlaylistView();
        updateContent(new MioloUpdated(playlistsView, "playlists", "Playlists", playlistsView.getFab()));
    }

    public void loadPlaylistCreateView() {
        CreatePlaylist createPlaylist = new CreatePlaylist();
        updateContent(new MioloUpdated(createPlaylist, "createPlaylist", "Criar Playlist"));
    }

    public void loadPlayListDetailView(Playlist playlist) {
        DetailPlaylist detailPlaylist = new DetailPlaylist(playlist);
        updateContent(new MioloUpdated(detailPlaylist, "detailPlaylist", playlist.getName()));
    }

    private void updateContent(MioloUpdated updatedContent) {
        updatedContent.setShowBackButton(true);
        contentChangedSubject.onNext(updatedContent);
    }

    public void setOnContentChanged(Consumer<MioloUpdated> consumer) {
        contentChangedSubject.subscribe(consumer);
    }
}
