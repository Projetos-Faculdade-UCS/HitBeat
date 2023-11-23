package hitbeat.view;

import java.util.HashMap;
import java.util.Map;

import hitbeat.controller.Icons;
import hitbeat.controller.MioloController;
import hitbeat.controller.MioloState;
import hitbeat.view.Player.Footer;
import hitbeat.view.base.widgets.Miolo;
import hitbeat.view.sidebar.Sidebar;
import hitbeat.view.sidebar.SidebarItem;
import hitbeat.view.sidebar.SidebarTopic;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class IndexView extends Application {
    private BorderPane root;
    private Scene scene;
    private Sidebar sidebar;
    private Miolo miolo;
    private final Icons icons = new Icons();
    private Map<String, SidebarItem> sidebarItems = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();
        miolo = new Miolo(new StartPage());
        MioloController controller = MioloController.getInstance();
        controller.setMiolo(miolo);
        this.setContentLayout((Pane) miolo);
        sidebar = setupSidebar();
        Footer footer = new Footer();

        root.setCenter(miolo);
        root.setLeft(sidebar);
        root.setBottom(footer);

        root.applyCss();
        root.layout();

        setupEventHandlers();

        setupScene(primaryStage);
    }

    private void setupEventHandlers() {
        MioloController controller = MioloController.getInstance();
        // controller.setContentChangedConsumer(this::updateContent);
        controller.setOnContentChanged(this::updateContent);
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
        sidebarItems.put("albums", new SidebarItem("Álbuns", icons.getAlbum(), controller::loadArtistsView));
        sidebarItems.put("favorites", new SidebarItem("Favoritas", icons.getFavorite(true), controller::loadFavoritesView));

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
                        sidebarItems.get("tracks"),
                        sidebarItems.get("favorites"),
                        sidebarItems.get("playlists"),
                        sidebarItems.get("genres"),
                        sidebarItems.get("artists")
                        ),
                new SidebarTopic(
                        "Gerenciar",
                        sidebarItems.get("library")));
    }

    private void setupScene(Stage primaryStage) {
        scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/hitbeat/css/index.css").toExternalForm());

        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);

        Image applicationIcon = new Image(getClass().getResourceAsStream("/hitbeat/images/hitbeat-icon.png"));
        primaryStage.getIcons().add(applicationIcon);

        scene.setFill(Color.TRANSPARENT);

        UserAgentBuilder.builder()
                .themes(JavaFXThemes.MODENA)
                .themes(MaterialFXStylesheets.forAssemble(true))
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        primaryStage.setScene(scene);
        primaryStage.setTitle("HitBeat");
        primaryStage.show();
    }

    public void updateContent(MioloState newContent) {
        sidebarItems.values().forEach(item -> item.setActive(false));

        if (newContent.getIdentifier() != null) {
            SidebarItem itemAtivo = sidebarItems.get(newContent.getIdentifier());
            if (itemAtivo != null) {
                itemAtivo.setActive(true);
            }
        }
    }

    private void setContentLayout(Pane contentWrapper) {
        Layout.getInstance().setContentWidth(contentWrapper.widthProperty());

        Layout.getInstance().setContentHeight(contentWrapper.heightProperty());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
