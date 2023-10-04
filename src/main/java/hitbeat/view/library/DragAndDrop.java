package hitbeat.view.library;

import hitbeat.controller.library.LibraryController;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class DragAndDrop extends StackPane {
    LibraryController controller;

    public DragAndDrop(LibraryController controller) {
        super();
        this.controller = controller;
        this.getStyleClass().add("drag-and-drop");

        HBox description = new HBox();
        description.setAlignment(Pos.CENTER);


        Image image = new Image(getClass().getResourceAsStream("/hitbeat/images/drag-and-drop-icon.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);

        Text text = new Text("Arraste a pasta para adicionar");
        text.getStyleClass().add("text");

        description.getChildren().addAll(imageView, text);

        this.setPrefHeight(74.4);
        this.setPrefWidth(Integer.MAX_VALUE);

        this.getChildren().add(description);


        this.setOnDragOver(event -> {
            if (event.getDragboard().hasFiles()) {
                event.acceptTransferModes(javafx.scene.input.TransferMode.COPY);
            }
            event.consume();
        });

        this.setOnDragEntered(event -> {
            if (event.getDragboard().hasFiles()) {
                this.getStyleClass().add("drag-over");
            }
            event.consume();
        });
        

        this.setOnDragExited(event -> {
            this.getStyleClass().remove("drag-over");
            event.consume();
        });

        this.setOnDragDropped((event) -> {
            this.getStyleClass().remove("drag-over");
            controller.dragEvent(event);
        });

        this.setOnMouseClicked((event) -> controller.addFolderToLibrary());
    }
    
}
