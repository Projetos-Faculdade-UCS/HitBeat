package hitbeat.view.tracks;

import hitbeat.controller.Icons;
import hitbeat.controller.player.PlayerController;
import hitbeat.controller.playlist.PlaylistController;
import hitbeat.controller.tracks.TracksController;
import hitbeat.model.Track;
import hitbeat.view.base.utils.MyButton;
import hitbeat.view.base.utils.MyContextMenu;
import hitbeat.view.base.utils.MyMenu;
import hitbeat.view.base.utils.MyMenuItem;
import hitbeat.view.base.widgets.ListTile;
import hitbeat.view.base.widgets.RoundedButton;
import hitbeat.view.base.widgets.SVGWidget;
import hitbeat.view.base.widgets.listview.BaseCell;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
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
    private PlaylistController playlistController = new PlaylistController();
    private TracksController tracksController = new TracksController();

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

        ListTile listTile = new ListTile(playbox, titleLabel, subtitleLabel, getMenuBtns());
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

    public HBox getMenuBtns() {
        Node trailingIcon = icons.getOptions(); 

        MyButton favoriteBtn = new MyButton("", icons.getFavorite(false));
        MyButton optionsBtn = new MyButton("", trailingIcon);

        MyContextMenu contextMenu = new MyContextMenu();
        MyMenu addMenu = new MyMenu("Adicionar à playlist");
        MyMenuItem removeItem = new MyMenuItem("Remover desta playlist");
        
        addMenu.getItems().add(new MenuItem("")); // nodo ancora
        addMenu.setOnShowing(event -> {
            addMenu.getItems().clear(); 
            playlistController.fetchAll().forEach(playlist -> {
                MyMenuItem item = new MyMenuItem(playlist.getName());
                item.setOnAction(event1 -> {
                    playlistController.addTrack(playlist, this.track);
                });
                addMenu.getItems().add(item);
            });
        });
        favoriteBtn.setOnMouseClicked(event -> {
            tracksController.toggleFavorite(this.track);
            favoriteBtn.setGraphic(icons.getFavorite(this.track.isFavorite()));
        });

        contextMenu.getItems().addAll(addMenu, removeItem);
        optionsBtn.setOnMouseClicked(event -> {
            contextMenu.show(optionsBtn, event.getScreenX(), event.getScreenY());
        });
        HBox menuBtns = new HBox(favoriteBtn, optionsBtn);

        return menuBtns;
    }
}
