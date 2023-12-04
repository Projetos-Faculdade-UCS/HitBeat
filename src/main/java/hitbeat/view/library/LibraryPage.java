package hitbeat.view.library;

import java.util.Map;

import hitbeat.controller.Icons;
import hitbeat.controller.MioloController;
import hitbeat.controller.library.LibraryController;
import hitbeat.util.CustomMP3File;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.FloatingActionButton;
import hitbeat.view.base.widgets.SVGWidget;
import hitbeat.view.tracks.TracksView;
import javafx.collections.ObservableList;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class LibraryPage extends VBox implements BaseView {

    private LibraryController controller;

    private MP3FileList mp3FileList;

    public LibraryPage() {
        super();

        controller = new LibraryController(this);

        initializeStyling();
        configureChildren();

        getStylesheets().add("hitbeat/css/library/library.css");

        MioloController.getInstance().setFab(getFab());
    }

    private void initializeStyling() {
        getStyleClass().add("library-page");
    }

    private void configureChildren() {
        addDragAndDrop();
        addFilesBoxToScrollPane();
    }

    private void addDragAndDrop() {
        DragAndDrop dragAndDrop = new DragAndDrop(controller);
        getChildren().add(dragAndDrop);
    }

    private void addFilesBoxToScrollPane() {
        createConfiguredScrollPane();

        // Set a preferred height to your MP3FileList
        mp3FileList.setPrefHeight(400); // You can adjust this value as needed

        // Set VBox constraints to make MP3FileList take available vertical space
        VBox.setVgrow(mp3FileList, Priority.ALWAYS);

        getChildren().add(mp3FileList);
    }

    private void createConfiguredScrollPane() {
        mp3FileList = new MP3FileList(controller);
    }

    public void setFilesFromFolder(ObservableList<CustomMP3File> files) {
        mp3FileList.setFiles(files);
    }

    @Override
    public Map<String, Object> getData() {
        return null;
    }

    public FloatingActionButton getFab() {
        FloatingActionButton addPlaylistButton = new FloatingActionButton();
        SVGWidget saveIcon = new Icons().getSave();
        addPlaylistButton.setIcon(saveIcon);
        addPlaylistButton.setOnAction(e -> {
            controller.saveToDatabase();
            setFilesFromFolder(controller.getFiles());
            MioloController.getInstance().push(new TracksView(), "tracks", "Todas Músicas");
        });

        return addPlaylistButton;
    }

}
