package hitbeat.view.playlists;

import java.util.Map;

import hitbeat.controller.Icons;
import hitbeat.controller.MioloController;
import hitbeat.controller.MioloState;
import hitbeat.controller.playlist.PlaylistController;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.FloatingActionButton;
import hitbeat.view.base.widgets.SVGWidget;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class CreatePlaylist extends MFXScrollPane implements BaseView {
    private final PlaylistController controller = new PlaylistController();
    private MFXTextField nameField = new MFXTextField();
    private MFXTextField descriptionField = new MFXTextField();
    private ImageView cover = new ImageView();
    private Image image;

    public CreatePlaylist() {
        super();

        nameField.setFloatingText("Nome");
        nameField.setPrefHeight(30);
        nameField.prefWidthProperty().bind(this.widthProperty());

        descriptionField.setFloatingText("Descrição");
        descriptionField.setPrefHeight(30);
        descriptionField.prefWidthProperty().bind(this.widthProperty());
        
        VBox txtFields = new VBox(); 
        txtFields.getChildren().addAll(nameField, descriptionField);
        txtFields.setSpacing(10);

        HBox formBox = new HBox();
        formBox.getChildren().addAll(getCoverField(), txtFields);
        formBox.setSpacing(10);

        this.getStylesheets().add(getClass().getResource("/hitbeat/css/form.css").toExternalForm());
        this.getStyleClass().add("form");
        this.setContent(formBox);

        this.setFitToWidth(true);
    }

    @Override
    public Map<String, Object> getData() {
        return null;
    }

    public FloatingActionButton getFab() {
        FloatingActionButton saveBtn = new FloatingActionButton();
        saveBtn.setIcon(new Icons().getSave());
        saveBtn.setOnAction(e -> {
            controller.createPlaylist(nameField.getText(), descriptionField.getText(), image);
            MioloController.getInstance().replace(
                new MioloState(new PlaylistView(), "playlists", "Playlists", PlaylistView.getFab())
            );
        });

        return saveBtn;
    }

    public StackPane getCoverField(){
        StackPane coverField = new StackPane();
        coverField.setAlignment(Pos.CENTER);
        coverField.getStyleClass().add("cover-field");
        SVGWidget editIcon = new Icons().getEdit();
        
        
        VBox editIconBox = new VBox(editIcon, new Label("Escolher capa"));
        editIconBox.setAlignment(Pos.CENTER);
        editIconBox.setSpacing(2);
        editIconBox.getStyleClass().add("new-playlist-cover-edit");
        coverField.getChildren().addAll(cover, editIconBox);

        coverField.setOnMouseClicked(e -> {
            controller.getImageFromDisk().ifPresent(image -> {
                setCoverImage(image);
            });
        });

        return coverField;
    }

    public void setCoverImage(Image image) {
        this.image = image;
        cover.setImage(this.image);
        cover.setFitWidth(100);
        cover.setPreserveRatio(true);

        Rectangle retangulo = new Rectangle(100, 100);
        retangulo.setArcWidth(10);
        retangulo.setArcHeight(10);

        cover.setClip(retangulo);
    }
}
