package hitbeat.view.playlists;

import hitbeat.controller.playlists.PlaylistsController;
import hitbeat.model.Playlist;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlaylistsView extends MFXScrollPane{
    private ObservableList<Playlist> playlists;
    private final PlaylistsController controller = new PlaylistsController();

    public PlaylistsView() {
        super();
        Text title = new Text("Nova Playlist");
        title.setStyle("-fx-font-size: 20px; -fx-text-fill: #2195f3;");
        
        MFXTextField nameField = new MFXTextField();
        nameField.setFloatingText("Nome");
        nameField.setPrefHeight(30);
        nameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #000000;");
        nameField.prefWidthProperty().bind(this.widthProperty());
        
        VBox page = new VBox(); 
        page.getChildren().addAll(title, nameField);

        this.setContent(page);
    }
}
