package hitbeat.controller;

import java.util.function.Consumer;
import java.util.function.Supplier;

import hitbeat.model.Playlist;
import hitbeat.view.StartPage;
import hitbeat.view.artists.ArtistsView;
import hitbeat.view.base.mementos.ContentCaretaker;
import hitbeat.view.base.mementos.ContentMemento;
import hitbeat.view.base.widgets.FloatingActionButton;
import hitbeat.view.base.widgets.Miolo;
import hitbeat.view.genres.GenresView;
import hitbeat.view.library.LibraryPage;
import hitbeat.view.playlists.CreatePlaylist;
import hitbeat.view.playlists.DetailPlaylist;
import hitbeat.view.playlists.PlaylistView;
import hitbeat.view.tracks.TracksView;
import javafx.application.Platform;

public class MioloController {
    private final ContentCaretaker caretaker = new ContentCaretaker();

    private Consumer<ContentUpdated> contentChangedConsumer;
    private Supplier<ContentMemento> stateSupplier;
    private Miolo miolo;

    // Callback set by the view
    private Consumer<ContentMemento> onRestoreCallback;

    private MioloController() {
    }

    private static class SingletonHelper {
        private static final MioloController INSTANCE = new MioloController();
    }

    public static MioloController getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void setOnRestoreCallback(Consumer<ContentMemento> callback) {
        this.onRestoreCallback = callback;
    }

    public void setMiolo(Miolo miolo) {
        this.miolo = miolo;
    }

    public void setFab(FloatingActionButton fab) {
        Platform.runLater(() -> {
            miolo.setFab(fab);
        });
    }

    public void loadStartPage() {
        StartPage startPage = new StartPage();
        updateContent(new ContentUpdated(startPage, "index"));
    }

    public void loadTracksView() {
        TracksView tracksView = new TracksView();
        updateContent(new ContentUpdated(tracksView, "tracks"));
    }

    public void loadGenresView() {
        GenresView genresView = new GenresView();
        updateContent(new ContentUpdated(genresView, "genres"));
    }

    public void loadLibraryView() {
        LibraryPage libraryPage = new LibraryPage();
        updateContent(new ContentUpdated(libraryPage, "library"));
    }

    public void loadArtistsView() {
        ArtistsView artistsView = new ArtistsView();
        updateContent(new ContentUpdated(artistsView, "artists"));
    }

    public void loadPlaylistsView() {
        PlaylistView playlistsView = new PlaylistView();
        updateContent(new ContentUpdated(playlistsView, "playlists"));
    }

    public void loadPlaylistCreateView() {
        CreatePlaylist createPlaylist = new CreatePlaylist();
        updateContent(new ContentUpdated(createPlaylist, "createPlaylist"));
    }

    public void loadPlayListDetailView(Playlist playlist) {
        DetailPlaylist detailPlaylist = new DetailPlaylist(playlist);
        updateContent(new ContentUpdated(detailPlaylist, "detailPlaylist"));
    }

    private void updateContent(ContentUpdated updatedContent) {
        miolo.removeFab();
        if (contentChangedConsumer != null) {
            saveCurrentState();
            contentChangedConsumer.accept(updatedContent);
        }
    }

    public void saveCurrentState() {
        if (stateSupplier != null) {
            ContentMemento memento = stateSupplier.get();
            caretaker.addMemento(memento);
        }
    }

    public boolean hasMemento() {
        return caretaker.hasMemento();
    }

    public void restoreLastState() {
        if (hasMemento() && onRestoreCallback != null) {
            onRestoreCallback.accept(caretaker.getLastMemento());
        }
    }

    public void setContentChangedConsumer(Consumer<ContentUpdated> consumer) {
        this.contentChangedConsumer = consumer;
    }

    public void setSaveStateRequestConsumer(Supplier<ContentMemento> consumer) {
        this.stateSupplier = consumer;
    }
}
