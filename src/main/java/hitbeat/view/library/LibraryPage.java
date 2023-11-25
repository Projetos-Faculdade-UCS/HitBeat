package hitbeat.view.library;

import hitbeat.controller.MioloController;
import hitbeat.controller.library.LibraryController;
import hitbeat.util.CustomMP3File;
import hitbeat.view.BaseView;
import hitbeat.view.base.widgets.FloatingActionButton;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class LibraryPage extends VBox implements BaseView{

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
    public Object getData() {
        return null;
    }

    public FloatingActionButton getFab(){
        FloatingActionButton addPlaylistButton = new FloatingActionButton();
        Image add = new Image("/hitbeat/images/save.png", 20, 20, false, false);
        addPlaylistButton.setIcon(add);
        addPlaylistButton.setOnAction(e -> {
            controller.saveToDatabase();
            setFilesFromFolder(controller.getFiles());
        });

        return addPlaylistButton;
    }

}
