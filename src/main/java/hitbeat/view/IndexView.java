package hitbeat.view;

import java.util.HashMap;
import java.util.Map;

import hitbeat.controller.ContentUpdated;
import hitbeat.controller.Icons;
import hitbeat.controller.MioloController;
import hitbeat.view.Player.Footer;
// ... Other imports ...
import hitbeat.view.base.mementos.ContentMemento;
import hitbeat.view.base.widgets.Miolo;
import hitbeat.view.base.widgets.SVGWidget;
import hitbeat.view.sidebar.Sidebar;
import hitbeat.view.sidebar.SidebarItem;
import hitbeat.view.sidebar.SidebarTopic;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private Miolo miolo;
    private final Icons icons = new Icons();
    private Map<String, SidebarItem> sidebarItems = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        miolo = new Miolo();
        MioloController.getInstance().setMiolo(miolo);
        this.setContentLayout((Pane) miolo);
        sidebar = setupSidebar();
        Footer footer = new Footer();

        root.setCenter(miolo);
        root.setLeft(sidebar);
        root.setBottom(footer);

        root.applyCss();
        root.layout();

        setupEventHandlers();

        // controller.setOnRestoreCallback(this::restoreFromMemento); // Setup the callback for restoration

        setupScene(primaryStage);
    }

    private void setupEventHandlers() {
        MioloController controller = MioloController.getInstance();
        controller.setContentChangedConsumer(this::updateContent);
        // controller.setSaveStateRequestConsumer(this::saveToMemento);
    }

    private Sidebar setupSidebar() {
        MioloController controller = MioloController.getInstance();

        SidebarItem index = new SidebarItem("Início", icons.getHome(), controller::loadStartPage);
        index.setActive(true);
        sidebarItems.put("index", index);
        sidebarItems.put("genres", new SidebarItem("Gêneros", icons.getGenres(), controller::loadGenresView));
        sidebarItems.put("tracks", new SidebarItem("Todas", icons.getTracks(), controller::loadTracksView));
        sidebarItems.put("library", new SidebarItem("Minha Biblioteca", null, controller::loadLibraryView));
        sidebarItems.put("artists", new SidebarItem("Artistas", icons.getArtists(), controller::loadArtistsView));
        sidebarItems.put("playlists",
                new SidebarItem("Playlists", icons.getPlaylists(), controller::loadPlaylistsView));

        ImageView logo = new ImageView("/hitbeat/images/hitbeat-icon.png");
        logo.setFitWidth(50);
        logo.setPreserveRatio(true);

        return new Sidebar(
                "HitBeat", logo,
                new SidebarTopic(
                        "Minhas Músicas",
                        sidebarItems.get("index"),
                        sidebarItems.get("genres"),
                        sidebarItems.get("tracks"),
                        sidebarItems.get("artists"),
                        sidebarItems.get("playlists")),
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

        Image applicationIcon = new Image(getClass().getResourceAsStream("/hitbeat/images/hitbeat-icon.png"));
        primaryStage.getIcons().add(applicationIcon);

        // Set border radius
        scene.setFill(Color.TRANSPARENT);

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA) // Optional if you don't need JavaFX's default theme, still recommended
                                             // though
                .themes(MaterialFXStylesheets.forAssemble(true)) // Adds the MaterialFX's default theme. The boolean
                                                                 // argument is to include legacy controls
                .setDeploy(true) // Whether to deploy each theme's assets on a temporary dir on the disk
                .setResolveAssets(true) // Whether to try resolving @import statements and resources urls
                .build() // Assembles all the added themes into a single CSSFragment (very powerful class
                         // check its documentation)
                .setGlobal();

        primaryStage.setScene(scene);
        primaryStage.setTitle("HitBeat");
        primaryStage.show();
    }

    public void updateContent(ContentUpdated newContent) {
        sidebarItems.values().forEach(item -> item.setActive(false));

        if (newContent.getIdentifier() != null) {
            SidebarItem itemAtivo = sidebarItems.get(newContent.getIdentifier());
            if (itemAtivo != null) {
                itemAtivo.setActive(true);
            }
        }

        this.miolo.setContent(newContent.getContent());
        // root.setCenter(wrapContentWithBackButton());
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
        updateContent(new ContentUpdated(memento.getContentState(), memento.getIdentifier()));
    }

    private Node unwrapContent(Node content) {
        if (content instanceof StackPane) {
            return ((StackPane) content).getChildren().get(0);
        }
        return content;
    }

    // private StackPane wrapContentWithBackButton() {
    //     // StackPane contentWrapper = new StackPane(this.content);

    //     // // Check if there are any saved mementos before adding the back button
    //     // if (controller.hasMemento()) {
    //     //     // set the content with a top padding of the size of the back button
    //     //     content.setStyle("-fx-padding: " + 2 * BACK_BUTTON_SIZE + " 0 0 0; -fx-background-color: transparent;");
    //     //     MFXButton backButton = createBackButton();
    //     //     contentWrapper.getChildren().add(backButton);
    //     // }

    //     // setContentLayout(contentWrapper);

    //     // return contentWrapper;
    // }

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

        // backButton.setOnAction(e -> controller.restoreLastState());

        return backButton;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
