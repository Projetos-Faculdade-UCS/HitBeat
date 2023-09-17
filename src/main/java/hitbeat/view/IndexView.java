package hitbeat.view;

import hitbeat.styles.Styles;
import hitbeat.view.base.mementos.ContentCaretaker;
import hitbeat.view.base.mementos.ContentMemento;
import hitbeat.view.base.widgets.Widget;
import hitbeat.view.base.widgets.sidebar.Sidebar;
import hitbeat.view.footer.Footer;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IndexView extends Application {
    private BorderPane root;
    private Scene scene;
    private Sidebar sidebar;
    private Node content;

    private ContentCaretaker caretaker = new ContentCaretaker();

    public IndexView() {
        super();
        content = new StartPage().build();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(10);
        MFXButton button1 = new MFXButton("Sidebar Item 1");
        MFXButton button2 = new MFXButton("Sidebar Item 2");
        // button colors
        button1.setMinWidth(200);
        button2.setMinWidth(200);

        button1.setStyle(Styles.SIDEBAR_BUTTONS); 
        button2.setStyle(Styles.SIDEBAR_BUTTONS); 
        
        sidebar.getChildren().addAll(button1, button2);

        // Content area
        CenterOne content = new CenterOne();

        Footer footer = new Footer();

        root.setLeft(sidebar);
        root.setCenter(content);
        root.setBottom(footer);

        Scene scene = new Scene(root, 800, 700);
        scene.getStylesheets().add(
                getClass().getResource("/hitbeat/css/index.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Index Page");
        primaryStage.show();

        // Activate MaterialFX
        activateMaterialFX();
    }

    public void restoreLastState() {
        content = restoreFromMemento(caretaker.getLastMemento());
        root.setCenter(content);
    }

    public void setContent(Widget contentWidget) {
        ContentMemento memento = saveToMemento(root.getCenter());
        caretaker.addMemento(memento);

        this.content = contentWidget.build();

        // Wrap content in StackPane
        StackPane contentWrapper = new StackPane();
        contentWrapper.getChildren().add(this.content);

        // Create the back button with the icon
        Image backButtonImage = new Image(getClass().getResourceAsStream("/hitbeat/png/back-button.png"));
        ImageView backButtonImageView = new ImageView(backButtonImage);
        backButtonImageView.setFitHeight(30); // Adjust size as needed
        backButtonImageView.setFitWidth(30); // Adjust size as needed

        // Change the color of the icon
        ColorAdjust colorAdjust = new ColorAdjust();
        // set to white
        colorAdjust.setHue(0);
        backButtonImageView.setEffect(colorAdjust);

        MFXButton backButton = new MFXButton("");
        backButton.setGraphic(backButtonImageView);
        backButton.setStyle("-fx-background-color: transparent;"); // Transparent background
        StackPane.setAlignment(backButton, Pos.TOP_LEFT);
        StackPane.setMargin(backButton, new Insets(10, 10, 10, 10));

        backButton.setOnAction(e -> restoreLastState());

        contentWrapper.getChildren().add(backButton); // Add the button to the content wrapper

        root.setCenter(contentWrapper);
    }

    public ContentMemento saveToMemento(Node state) {
        return new ContentMemento(state);
    }

    public static Node restoreFromMemento(ContentMemento memento) {
        return memento.getContentState();
    }

    private void activateMaterialFX() {
        MFXThemeManager.addOn(scene, Themes.DEFAULT);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
