package hitbeat.controller;

import java.util.Stack;

import hitbeat.view.StartPage;
import hitbeat.view.base.widgets.FloatingActionButton;
import hitbeat.view.base.widgets.Miolo;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import javafx.application.Platform;
import javafx.scene.Node;

public class MioloController {
    private BehaviorSubject<MioloState> contentChangedSubject = BehaviorSubject.create();
    private Miolo miolo;
    private Stack<MioloStateMemento> mementoStack = new Stack<>();

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

        MioloState state = new MioloState(startPage, "index", "Início");

        
            mementoStack.clear();

            MioloStateMemento memento = state.createMemento();
            mementoStack.push(memento);

        updateMiolo(state, true);
    }

    public void push(Node node, String identifier, String title) {
        MioloState state = new MioloState(node, identifier, title);
        updateMiolo(state);
    }

    public void push(Node node, String identifier, String title, FloatingActionButton fab) {
        MioloState state = new MioloState(node, identifier, title, fab);
        updateMiolo(state);
    }

    private void updateMiolo(MioloState updatedContent, boolean... isStartPage) {
        MioloState currentState = getCurrentState();
        if (currentState != null && isStartPage.length == 0) {
            mementoStack.push(currentState.createMemento());
        }

        updatedContent.setShowBackButton(mementoStack.size() > 1);
        contentChangedSubject.onNext(updatedContent);
    }

    public void setOnContentChanged(Consumer<MioloState> consumer) {
        contentChangedSubject.subscribe(consumer);
    }

    public MioloState getCurrentState() {
        return contentChangedSubject.getValue();
    }

    public void restoreFromMemento() {
        if (mementoStack.size() < 1) {
            return;
        }
        MioloStateMemento memento = mementoStack.pop();
        MioloState mioloState = new MioloState(memento.getContent(), memento.getIdentifier(), memento.getTitle(),
                memento.getFab());
        mioloState.setShowBackButton(memento.getShowBackButton());
        contentChangedSubject.onNext(mioloState);
    }

    public void replaceFromMemento(MioloState mioloState) {
        if (mementoStack.size() < 1) {
            return;
        }
        mementoStack.pop();
        mementoStack.push(mioloState.createMemento());
        contentChangedSubject.onNext(mioloState);
    }
}
