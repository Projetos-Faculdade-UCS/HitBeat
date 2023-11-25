package hitbeat.controller;

import hitbeat.view.base.widgets.FloatingActionButton;
import javafx.scene.Node;

public class MioloStateMemento {
    private Node content;
    private String identifier;
    private String title;
    private FloatingActionButton fab;
    private Boolean showBackButton;

    public MioloStateMemento(Node content, String identifier, String title, FloatingActionButton fab,
            Boolean showBackButton) {
        this.content = content;
        this.identifier = identifier;
        this.title = title;
        this.fab = fab;
        this.showBackButton = showBackButton;
    }

    public Node getContent() {
        return content;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getTitle() {
        return title;
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    public Boolean getShowBackButton() {
        return showBackButton;
    }
}
