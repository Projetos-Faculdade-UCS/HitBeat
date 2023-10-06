package hitbeat.view.library;

import hitbeat.controller.library.SongEditRowController;
import hitbeat.util.CustomMP3File;
import hitbeat.view.base.widgets.listview.BaseCell;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SongEditRow extends BaseCell<CustomMP3File> {

    private VBox root;
    private SongEditRowController controller;
    private Field titleField;
    private Field genreField;
    private Field artistField;
    private Field filePathField;
    private int deferredLayoutPasses = 0;

    public SongEditRow(CustomMP3File file) {
        // Initialize UI components
        initUI();

        // Set file data
        updateItem(file);
    }

    private void initUI() {
        root = new VBox();
        root.getStyleClass().add("song-edit-row");
        this.getChildren().add(root);

        this.controller = new SongEditRowController(null); // We will set the file later

        initializeFields();
        addFieldsToLayout();
        if (deferredLayoutPasses == 0) {
            System.out.println("request layout");
            Platform.runLater(() -> {
                requestLayout();
                deferredLayoutPasses++;
            });
        }
    }

    private void initializeFields() {
        titleField = createField("", "Título", controller::titleTextListener);
        genreField = createField("", "Gênero", controller::genreTextListener);
        artistField = createField("", "Artista", controller::artistTextListener);
        filePathField = createField("", "Caminho do arquivo", null);
        filePathField.setEnabled(false);
    }

    private Field createField(String text, String title, ChangeListener<String> listener) {
        return new Field(text, createTextWithTitleStyle(title), listener);
    }

    private Text createTextWithTitleStyle(String title) {
        Text text = new Text(title);
        text.getStyleClass().add("text-field-title");
        return text;
    }

    private void addFieldsToLayout() {
        root.getChildren().addAll(titleField, genreField, artistField, filePathField);
        root.setSpacing(10);
    }

    @Override
    public void updateItem(CustomMP3File file) {
        if (file != null) {
            controller.setFile(file);
            titleField.setText(file.getTitle());
            genreField.setText(file.getGenre());
            artistField.setText(file.getArtist());
            filePathField.setText(file.getFilePath());
        } else {
            titleField.setText("");
            genreField.setText("");
            artistField.setText("");
            filePathField.setText("");
        }
    }

    class Field extends VBox {
        private final MFXTextField field;

        public Field(String text, Text fieldTitle, ChangeListener<String> listener) {
            field = new MFXTextField(text);
            setupTextField(listener);
            this.getChildren().addAll(fieldTitle, field);
        }

        private void setupTextField(ChangeListener<String> listener) {
            field.setPrefHeight(30);
            field.setMinHeight(30);
            field.setMaxHeight(30);
            field.prefWidthProperty().bind(SongEditRow.this.widthProperty().subtract(28));
            if (listener != null) {
                field.textProperty().addListener(listener);
            }
        }

        public void setText(String text) {
            field.setText(text);
        }

        public void setEnabled(boolean enabled) {
            field.setDisable(!enabled);
        }
    }
}
