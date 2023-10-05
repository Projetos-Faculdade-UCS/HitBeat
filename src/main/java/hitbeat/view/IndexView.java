package hitbeat.view;

import hitbeat.controller.IndexController;
// ... Other imports ...
import hitbeat.view.base.mementos.ContentMemento;
import hitbeat.view.base.widgets.SVGWidget;
import hitbeat.view.footer.Footer;
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
    private final IndexController controller = new IndexController(this);

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

        setupScene(primaryStage);
    }

    private Sidebar setupSidebar() {
        return new Sidebar(
                "HitBeat",
                new SidebarTopic(
                        "Menu 1",
                        new SidebarItem("Index", null, controller::loadStartPage),
                        new SidebarItem("Músicas", null, controller::loadTracksView),
                        new SidebarItem("Gêneros", null, controller::loadGenresView)),
                new SidebarTopic(
                        "Gerenciar",
                        new SidebarItem("Minha Biblioteca", null, controller::loadLibraryView)));
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

    public void updateContent(Node newContent) {
        this.content = newContent;
        root.setCenter(wrapContentWithBackButton());
    }

    public ContentMemento saveToMemento() {
        return new ContentMemento(unwrapContent(root.getCenter()));
    }

    public static Node restoreFromMemento(ContentMemento memento) {
        return memento.getContentState();
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
