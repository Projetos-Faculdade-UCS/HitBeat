package hitbeat.controller;

import hitbeat.view.IndexView;
import hitbeat.view.StartPage;
import hitbeat.view.base.mementos.ContentCaretaker;
import hitbeat.view.genres.GenresView;
import hitbeat.view.library.LibraryPage;
import hitbeat.view.tracks.TracksView;
import javafx.scene.Node;

public class IndexController {

    private final IndexView view;
    private final ContentCaretaker caretaker = new ContentCaretaker();

    public IndexController(IndexView view) {
        this.view = view;
    }

    public void loadStartPage() {
        StartPage startPage = new StartPage();
        setContent(startPage);
    }

    public void loadTracksView() {
        TracksView tracksView = new TracksView();
        setContent(tracksView);
    }

    public void loadGenresView() {
        GenresView centerOne = new GenresView();
        setContent(centerOne);
    }

    public void loadLibraryView() {
        LibraryPage libraryPage = new LibraryPage();
        setContent(libraryPage);
    }

    public void restoreLastState() {
        Node content = IndexView.restoreFromMemento(caretaker.getLastMemento());
        view.updateContent(content);
    }

    public boolean hasMemento() {
        return caretaker.hasMemento();
    }

    private void setContent(Node contentWidget) {
        caretaker.addMemento(view.saveToMemento());
        view.updateContent(contentWidget);
    }
}
