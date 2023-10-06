package hitbeat.controller;

import javafx.scene.Node;

public class ContentUpdated {
    private Node content;
    private String identifier;

    public ContentUpdated(Node content, String identifier) {
        this.content = content;
        this.identifier = identifier;
    }

    public Node getContent() {
        return content;
    }

    public String getIdentifier() {
        return identifier;
    }
}
