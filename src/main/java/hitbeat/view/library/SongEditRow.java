package hitbeat.view.library;

import hitbeat.controller.library.SongEditRowController;
import hitbeat.util.CustomMP3File;
import hitbeat.view.Layout;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SongEditRow extends VBox {

    private SongEditRowController controller;
    private Field titleField;
    private Field genreField;
    private Field filePathField;

    public SongEditRow(CustomMP3File file) {
        super();
        this.controller = new SongEditRowController(file);

        initializeFields(file);
        addFieldsToLayout();

        this.getStyleClass().add("song-edit-row");

        Layout.getInstance().getContentWidth();
    }

    private void initializeFields(CustomMP3File file) {
        titleField = createField(file != null ? file.getTitle() : "", "Título", controller::titleTextListener);
        genreField = createField(file != null ? file.getGenre() : "", "Gênero", controller::genreTextListener);
        filePathField = createField(file != null ? file.getFilePath() : "", "Caminho do arquivo", null);
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
        this.getChildren().addAll(titleField, genreField, filePathField);
        this.setSpacing(10);
    }

    public void updateFile(CustomMP3File file) {
        controller.setFile(file);
        titleField.setText(file.getTitle());
        genreField.setText(file.getGenre());
        filePathField.setText(file.getFilePath());
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
            field.prefWidthProperty().bind(SongEditRow.this.widthProperty().subtract(10));
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
