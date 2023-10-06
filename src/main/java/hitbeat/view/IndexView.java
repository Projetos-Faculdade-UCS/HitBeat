package hitbeat.view;

import java.util.HashMap;
import java.util.Map;

import hitbeat.controller.ContentUpdated;
import hitbeat.controller.Icons;
import hitbeat.controller.IndexController;
import hitbeat.view.Player.Footer;
// ... Other imports ...
import hitbeat.view.base.mementos.ContentMemento;
import hitbeat.view.base.widgets.SVGWidget;
import hitbeat.view.sidebar.Sidebar;
import hitbeat.view.sidebar.SidebarItem;
import hitbeat.view.sidebar.SidebarTopic;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IndexView extends Application {
    private static final double BACK_BUTTON_SIZE = 30;

    private BorderPane root;
    private Scene scene;
    private Sidebar sidebar;
    private Node content;
    private final IndexController controller = new IndexController();
    private final Icons icons = new Icons();
    private Map<String, SidebarItem> sidebarItems = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        content = new StartPage();
        this.setContentLayout((Pane) content);
        sidebar = setupSidebar();
        Footer footer = new Footer();

        root.setCenter(content);
        root.setLeft(sidebar);
        root.setBottom(footer);

        root.applyCss();
        root.layout();

        setupEventHandlers();

        controller.setOnRestoreCallback(this::restoreFromMemento); // Setup the callback for restoration

        setupScene(primaryStage);
    }

    private void setupEventHandlers() {
        controller.setContentChangedConsumer(this::updateContent);
        controller.setSaveStateRequestConsumer(this::saveToMemento);
    }

    private Sidebar setupSidebar() {
        SidebarItem index = new SidebarItem("Início", icons.getHome(), controller::loadStartPage);
        index.setActive(true);
        sidebarItems.put("index", index);
        sidebarItems.put("genres", new SidebarItem("Gêneros", icons.getGenres(), controller::loadGenresView));
        sidebarItems.put("tracks", new SidebarItem("Todas", icons.getTracks(), controller::loadTracksView));
        sidebarItems.put("library", new SidebarItem("Minha Biblioteca", null, controller::loadLibraryView));

        return new Sidebar(
                "HitBeat",
                new SidebarTopic(
                        "Minhas Músicas",
                        sidebarItems.get("index"),
                        sidebarItems.get("genres"),
                        sidebarItems.get("tracks")),
                new SidebarTopic(
                        "Gerenciar",
                        sidebarItems.get("library")));
    }

    private void setupScene(Stage primaryStage) {
        scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/hitbeat/css/index.css").toExternalForm());

        // min scene size
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);

        // Set border radius
        scene.setFill(Color.TRANSPARENT);

        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        primaryStage.setScene(scene);
        primaryStage.setTitle("HitBeat");
        primaryStage.show();
    }

    public void updateContent(ContentUpdated newContent) {
        sidebarItems.values().forEach(item -> item.setActive(false));

        if (newContent.getIdentifier() != null) {
            sidebarItems.get(newContent.getIdentifier()).setActive(true);
        }

        System.out.println("Updating content to: " + newContent.getContent());
        this.content = newContent.getContent();
        root.setCenter(wrapContentWithBackButton());
    }

    public ContentMemento saveToMemento() {
        String currentIdentifier = null;
        for (Map.Entry<String, SidebarItem> entry : sidebarItems.entrySet()) {
            if (entry.getValue().getStyleClass().contains("active")) {
                currentIdentifier = entry.getKey();
                break;
            }
        }
        return new ContentMemento(unwrapContent(root.getCenter()), currentIdentifier);
    }

    private void restoreFromMemento(ContentMemento memento) {
        System.out.println("Restoring from memento");

        System.out.println("Memento content: " + memento.getContentState());
        System.out.println("Memento identifier: " + memento.getIdentifier());
        updateContent(new ContentUpdated(memento.getContentState(), memento.getIdentifier()));
    }

    private Node unwrapContent(Node content) {
        if (content instanceof StackPane) {
            return ((StackPane) content).getChildren().get(0);
        }
        return content;
    }

    private StackPane wrapContentWithBackButton() {
        StackPane contentWrapper = new StackPane(this.content);

        // Check if there are any saved mementos before adding the back button
        if (controller.hasMemento()) {
            // set the content with a top padding of the size of the back button
            content.setStyle("-fx-padding: " + 2 * BACK_BUTTON_SIZE + " 0 0 0; -fx-background-color: transparent;");
            MFXButton backButton = createBackButton();
            contentWrapper.getChildren().add(backButton);
        }

        setContentLayout(contentWrapper);

        return contentWrapper;
    }

    private void setContentLayout(Pane contentWrapper) {
        Layout.getInstance().setContentWidth(contentWrapper.widthProperty());

        Layout.getInstance().setContentHeight(contentWrapper.heightProperty());
    }

    private MFXButton createBackButton() {
        SVGWidget backButtonIcon = new SVGWidget("/hitbeat/svg/back-button.svg", BACK_BUTTON_SIZE, Color.WHITE);

        MFXButton backButton = new MFXButton("");
        backButton.setGraphic(backButtonIcon);
        backButton.setStyle("-fx-background-color: transparent;");
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(10));

        backButton.setOnAction(e -> controller.restoreLastState());

        return backButton;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
