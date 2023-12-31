package hitbeat.controller;

import java.util.Map;

import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.FloatingActionButton;
import javafx.scene.Node;
import lombok.Getter;

@Getter
public class MioloState{
    private Node content;
    private String identifier;
    private String title;
    private FloatingActionButton fab;
    private Boolean showBackButton = false;


    public MioloState(Node content, String identifier, String title) {
        this.content = content;
        this.identifier = identifier;
        this.title = title;
    }

    public MioloState(Node content, String identifier, String title, FloatingActionButton fab) {
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

    /*
     * Retorna as informações do conteúdo atual, em formato de map,
     * assim pode conter qualquer tipo de dado.
     * @return Object
     */
    public Map<String, Object> getData() {
        Map<String, Object> data = null;
    
        if (content instanceof BaseView) {
            data = ((BaseView) content).getData();
        }

        return data;
    }

    public MioloStateMemento createMemento() {
        return new MioloStateMemento(content, identifier, title, fab, showBackButton);
    }

    public void restoreFromMemento(MioloStateMemento memento) {
        this.content = memento.getContent();
        this.identifier = memento.getIdentifier();
        this.title = memento.getTitle();
        this.fab = memento.getFab();
        this.showBackButton = memento.getShowBackButton();
    }
}
