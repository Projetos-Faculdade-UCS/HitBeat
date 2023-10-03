package hitbeat.view.library;

import hitbeat.controller.library.SongEditRowController;
import hitbeat.util.CustomMP3File;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SongEditRow extends GridPane {

    private SongEditRowController controller;

    public SongEditRow(CustomMP3File file) {
        super();
        this.controller = new SongEditRowController(file);

        this.setHgap(10);
        this.setVgap(10);

        Field titleField = new Field(file.getTitle(), "Título");

        Field genreField = new Field(file.getGenre(), "Gênero");

        Field filePathField = new Field(file.getFilePath(), "Caminho do arquivo");
        filePathField.setEnabled(false);

        titleField.prefWidthProperty().bind(this.widthProperty().subtract(10));
        genreField.prefWidthProperty().bind(this.widthProperty().subtract(10));
        filePathField.prefWidthProperty().bind(this.widthProperty().subtract(10));

        this.add(titleField, 0, 0);
        this.add(genreField, 0, 1);
        this.add(filePathField, 0, 2);

        this.getStyleClass().add("song-edit-row");
    }

    public SongEditRowController getController() {
        return controller;
    }

    class Field extends VBox {
        private final MFXTextField field;

        public Field(String text, String title) {
            super();

            Text fieldTitle = new Text(title);
            fieldTitle.getStyleClass().add("text-field-title");
            this.getChildren().add(fieldTitle);

            field = new MFXTextField();
            field.setText(text);
            field.prefWidthProperty().bind(this.widthProperty());
            
            field.textProperty().addListener(controller::textListener);
            
            field.setPrefHeight(30);
            field.setPadding(Insets.EMPTY);
            field.setMinHeight(30);
            field.setMaxHeight(30);

            GridPane.setValignment(field, VPos.CENTER);

            this.getChildren().add(field);
        }

        public void setEnabled(boolean enabled) {
            field.setDisable(!enabled);
        }

    }
}
