package hitbeat.view.playlists;

import hitbeat.controller.IndexController;
import hitbeat.controller.playlist.PlaylistController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CreatePlaylist extends MFXScrollPane{
    // private ObservableList<Playlist> playlists;
    private final PlaylistController controller = new PlaylistController();
    private final IndexController mioloController = IndexController.getInstance();

    public CreatePlaylist() {
        super();
        Text title = new Text("Nova Playlist");
        title.setStyle("-fx-font-size: 24px;");
        title.setFill(Color.WHITE);
        
        MFXTextField nameField = new MFXTextField();
        nameField.setFloatingText("Nome");
        nameField.setPrefHeight(30);
        nameField.setStyle("-fx-background-color: transparent; -fx-text-fill: #000000;");
        nameField.prefWidthProperty().bind(this.widthProperty());
        
        MFXButton saveButton = new MFXButton("Salvar");
        saveButton.setStyle("-fx-background-color: #2195f3; -fx-text-fill: #ffffff;");
        saveButton.onMouseClickedProperty().set((event) -> {
            controller.createPlaylist(nameField.getText());
            mioloController.loadPlaylistsView();
        });
        VBox page = new VBox(); 
        page.getChildren().addAll(title, nameField, saveButton);

        this.setContent(page);
    }
}
