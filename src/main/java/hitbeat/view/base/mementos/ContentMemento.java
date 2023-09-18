package hitbeat.view.base.mementos;

import javafx.scene.Node;

public class ContentMemento {
    private Node contentState;

    public ContentMemento(Node contentState) {
        this.contentState = contentState;
    }

    public Node getContentState() {
        return contentState;
    }
}