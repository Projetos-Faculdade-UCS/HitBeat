package hitbeat.controller;

import hitbeat.view.base.widgets.FloatingActionButton;
import javafx.scene.Node;
import lombok.Getter;

@Getter
public class MioloUpdated {
    private Node content;
    private String identifier;
    private String title;
    private FloatingActionButton fab;
    private Boolean showBackButton = false;

    public MioloUpdated(Node content, String identifier, String title) {
        this.content = content;
        this.identifier = identifier;
        this.title = title;
    }

    public MioloUpdated(Node content, String identifier, String title, FloatingActionButton fab) {
        this.content = content;
        this.identifier = identifier;
        this.title = title;
        this.fab = fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    public void setShowBackButton(Boolean showBackButton) {
        this.showBackButton = showBackButton;
    }
}
