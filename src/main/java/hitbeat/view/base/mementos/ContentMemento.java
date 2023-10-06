package hitbeat.view.base.mementos;

import javafx.scene.Node;

public class ContentMemento {
    private Node contentState;
    private String identifier;

    public ContentMemento(Node contentState, String identifier) {
        this.contentState = contentState;
        this.identifier = identifier;
    }

    public Node getContentState() {
        return contentState;
    }

    public String getIdentifier() {
        return identifier;
    }
}