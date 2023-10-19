package hitbeat.controller;

import java.util.function.Consumer;
import java.util.function.Supplier;

import hitbeat.view.StartPage;
import hitbeat.view.artists.ArtistsView;
import hitbeat.view.base.mementos.ContentCaretaker;
import hitbeat.view.base.mementos.ContentMemento;
import hitbeat.view.genres.GenresView;
import hitbeat.view.library.LibraryPage;
import hitbeat.view.tracks.TracksView;

public class IndexController {
    private final ContentCaretaker caretaker = new ContentCaretaker();

    private Consumer<ContentUpdated> contentChangedConsumer;
    private Supplier<ContentMemento> stateSupplier;

    // Callback set by the view
    private Consumer<ContentMemento> onRestoreCallback;

    public void setOnRestoreCallback(Consumer<ContentMemento> callback) {
        this.onRestoreCallback = callback;
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

    private void updateContent(ContentUpdated updatedContent) {
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
