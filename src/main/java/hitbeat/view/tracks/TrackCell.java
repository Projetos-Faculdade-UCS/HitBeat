package hitbeat.view.tracks;

import hitbeat.controller.Icons;
import hitbeat.controller.player.PlayerController;
import hitbeat.controller.playlist.PlaylistsController;
import hitbeat.model.Track;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.RoundedButton;
import hitbeat.view.base.widgets.SVGWidget;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class TrackCell extends BaseCell<Track> {
    private Track track;
    private Label titleLabel;
    private Label subtitleLabel;
    private Label trailingLabel;
    private Icons icons = new Icons();
    private PlaylistsController playlistsController = new PlaylistsController();

    public TrackCell(Track track) {
        // Initialize UI components
        initUI();

        // Set track data
        updateItem(track);
    }

    private void initUI() {
        // Create Leading
        RoundedButton playBtn = new RoundedButton("");
        SVGWidget svgPlay = new SVGWidget("/hitbeat/svg/play.svg", 25, Color.WHITE);
        playBtn.setGraphic(svgPlay);

        StackPane playbox = new StackPane(playBtn);
        VBox.setVgrow(playbox, Priority.ALWAYS);

        playbox.setAlignment(Pos.CENTER);
        playbox.setPickOnBounds(false);

        playBtn.setOnMouseClicked(event -> {
            PlayerController.getInstance().playSingleTrack(this.track);
        });

        // Create Title
        titleLabel = new Label();
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: white;");

        // Create Subtitle
        subtitleLabel = new Label();
        subtitleLabel.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        // Create Trailing
        icons = new Icons();

        

        ListTile listTile = new ListTile(playbox, titleLabel, subtitleLabel, getMenuBtn());
        this.getChildren().add(listTile);
    }

    @Override
    public void updateItem(Track track) {
        this.track = track;

        if (track != null) {
            titleLabel.setText(this.track.getName());
            if (this.track.getArtist() != null) {
                subtitleLabel.setText(this.track.getArtist().getName()); // Update if Track has more data
            }
        } else {
            titleLabel.setText("");
            subtitleLabel.setText("");
        }
    }

    public MenuButton getMenuBtn() {
        Node trailingIcon = icons.getOptions(); 
        MenuButton optionsBtn = new MenuButton("", trailingIcon, null);
        
        Menu addToPlaylistMenu = new Menu("Adicionar à playlist");

        MenuItem remove = new MenuItem("Remover desta playlist");
        MenuItem favorite = new MenuItem("Adicionar aos favoritos");

        addToPlaylistMenu.getItems().add(new MenuItem("")); // nodo ancora
        addToPlaylistMenu.setOnShowing(event -> {
            addToPlaylistMenu.getItems().clear(); 
            playlistsController.fetchAll().forEach(playlist -> {
                MenuItem item = new MenuItem(playlist.getName());
                item.setOnAction(event1 -> {
                    // playlistsController.addTrack(playlist, this.track);
                });
                addToPlaylistMenu.getItems().add(item);
            });
        });

        optionsBtn.getItems().addAll(addToPlaylistMenu, remove, favorite);
        return optionsBtn;
    }
}
