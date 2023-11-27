package hitbeat.view;

import java.util.HashMap;
import java.util.Map;

import hitbeat.controller.Icons;
import hitbeat.controller.MioloController;
import hitbeat.controller.MioloState;
import hitbeat.view.Player.Footer;
import hitbeat.view.albums.AlbumsView;
import hitbeat.view.artists.ArtistsView;
import hitbeat.view.base.widgets.Miolo;
import hitbeat.view.favorites.FavoritesView;
import hitbeat.view.genres.GenresView;
import hitbeat.view.library.LibraryPage;
import hitbeat.view.playlists.PlaylistView;
import hitbeat.view.sidebar.Sidebar;
import hitbeat.view.sidebar.SidebarItem;
import hitbeat.view.sidebar.SidebarTopic;
import hitbeat.view.tracks.TracksView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class IndexView extends BorderPane {
    private Sidebar sidebar;
    private Miolo miolo;
    private final Icons icons = new Icons();
    private Map<String, SidebarItem> sidebarItems = new HashMap<>();

    public IndexView() {
        miolo = new Miolo();
        MioloController controller = MioloController.getInstance();
        controller.setMiolo(miolo);
        this.setContentLayout((Pane) miolo);
        sidebar = setupSidebar();
        Footer footer = new Footer();

        this.setCenter(miolo);
        this.setLeft(sidebar);
        this.setBottom(footer);

        this.applyCss();
        this.layout();

        setupEventHandlers();

        controller.loadStartPage();
    }

    private void setupEventHandlers() {
        MioloController controller = MioloController.getInstance();
        controller.setOnContentChanged(this::updateContent);
    }

    private Sidebar setupSidebar() {
        MioloController controller = MioloController.getInstance();

        SidebarItem index = new SidebarItem("Início", icons.getHome(), controller::loadStartPage);
        index.setActive(true);
        sidebarItems.put("index", index);
        sidebarItems.put("genres", new SidebarItem("Gêneros", icons.getGenres(), () -> {
            controller.push(new GenresView(), "genres", "Gêneros");
        }));
        sidebarItems.put("tracks", new SidebarItem("Todas", icons.getTracks(), () -> {
            controller.push(new TracksView(), "tracks", "Todas");
        }));
        sidebarItems.put("library", new SidebarItem("Minha Biblioteca", null, () -> {
            controller.push(new LibraryPage(), "library", "Minha Biblioteca");
        }));
        sidebarItems.put("artists", new SidebarItem("Artistas", icons.getArtists(), () -> {
            controller.push(new ArtistsView(), "artists", "Artistas");
        }));
        sidebarItems.put("albums", new SidebarItem("Álbuns", icons.getAlbum(), () -> {
            controller.push(new AlbumsView(), "albums", "Álbuns");
        }));
        sidebarItems.put("favorites",
                new SidebarItem("Favoritas", icons.getFavorite(true), () -> {
                    controller.push(new FavoritesView(), "favorites", "Favoritas");
                }));

        sidebarItems.put("playlists",
                new SidebarItem("Playlists", icons.getPlaylists(), () -> {
                    controller.push(new PlaylistView(), "playlists", "Playlists", PlaylistView.getFab());
                }));

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
                        sidebarItems.get("artists"),
                        sidebarItems.get("albums")),
                new SidebarTopic(
                        "Gerenciar",
                        sidebarItems.get("library")));
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
}
