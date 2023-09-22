package hitbeat.view.base.mementos;

import java.util.Stack;

public class ContentCaretaker {
    private final Stack<ContentMemento> mementos = new Stack<>();

    public void addMemento(ContentMemento memento) {
        mementos.push(memento);
    }

    public ContentMemento getLastMemento() {
        return mementos.pop();
    }

    public boolean hasMemento() {
        return !mementos.isEmpty();
    }
}