package hitbeat.view.sidebar;

import java.util.ArrayList;
import java.util.Collections;

import hitbeat.view.base.widgets.Widget;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Sidebar extends Widget {

    private final String title;
    private final ArrayList<SidebarTopic> topics = new ArrayList<>();
    private VBox topicsSidebar;
    private final PauseTransition delay = new PauseTransition(Duration.millis(100));
    private double totalTopicsHeight = 0.0;
    private final InvalidationListener heightInvalidationListener = obs -> delay.playFromStart();

    public Sidebar(String title, SidebarTopic... topics) {
        this.title = title;
        Collections.addAll(this.topics, topics);
        initializeDelay();
    }

    @Override
    public Node build() {
        VBox sidebar = new VBox(10);
        sidebar.setStyle("-fx-background-color: #1e1e1e;");
        
        topicsSidebar = new VBox();
        sidebar.getChildren().add(new SidebarTitle(title).build());
        addTopicsWithSpacersToSidebar(topicsSidebar);
        sidebar.getChildren().add(topicsSidebar);

        deferSpacerAdjustment(sidebar);

        return sidebar;
    }

    private void initializeDelay() {
        delay.setOnFinished(event -> adjustSpacers());
    }

    private void addTopicsWithSpacersToSidebar(VBox sidebar) {
        for (SidebarTopic topic : topics) {
            Node node = topic.build();
            node.applyCss();
            if (node instanceof Parent) {
                ((Parent) node).layout();
            }
            sidebar.getChildren().add(node);
            totalTopicsHeight += node.getBoundsInParent().getHeight();  // update the total height
            sidebar.getChildren().add(createSpacer());
        }
        if (!topics.isEmpty()) {
            sidebar.getChildren().remove(sidebar.getChildren().size() - 1);  // Remove the last spacer
        }
    }

    private Region createSpacer() {
        return new Region();
    }

    private double calculateSpacerSize(double sidebarHeight) {
        double totalHeight = sidebarHeight;
        double totalWhiteSpace = totalHeight - totalTopicsHeight;
        return totalWhiteSpace / (topics.size() - 1);
    }

    private void deferSpacerAdjustment(VBox sidebar) {
        Platform.runLater(() -> {
            double spacerSize = calculateSpacerSize(sidebar.getHeight());
            setAllSpacersSize(spacerSize);
            topicsSidebar.heightProperty().addListener(heightInvalidationListener);
        });
    }
    

    private void setAllSpacersSize(double spacerSize) {
        for (Node node : topicsSidebar.getChildren()) {
            if (node instanceof Region) {
                ((Region) node).setPrefHeight(spacerSize);
            }
        }
    }

    private void adjustSpacers() {
        double spacerSize = calculateSpacerSize(topicsSidebar.getHeight());
        setAllSpacersSize(spacerSize);
    }
}
