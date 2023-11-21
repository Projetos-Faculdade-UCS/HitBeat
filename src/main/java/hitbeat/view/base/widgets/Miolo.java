package hitbeat.view.base.widgets;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label; // Add this import
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Miolo extends StackPane {
    private Header header = new Header();
    private FabContainer fab = new FabContainer();
    private Node content = new Region();
    private VBox contentContainer = new VBox();

    public Miolo(Node initialPage) {
        super();
    
        this.setAlignment(Pos.CENTER);
    
        contentContainer.setAlignment(Pos.TOP_LEFT);
        contentContainer.setSpacing(0);
        contentContainer.setPadding(new Insets(0));
        contentContainer.getChildren().addAll(this.header, this.content);
    
        VBox.setVgrow(content, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(contentContainer, javafx.scene.layout.Priority.ALWAYS);
    
        this.getChildren().addAll(contentContainer, fab);
    
        setOnBackClickedCallback(() -> {
            System.out.println("Back clicked");
        });
    
        // Set the initial content
        setContent(initialPage);
    }

    public void setFab(FloatingActionButton fab) {
        this.fab.setFab(fab);
        removeFab();
        this.getChildren().add(this.fab);
    }

    public void removeFab() {
        this.getChildren().remove(this.fab);
    }

    public void setContent(Node content) {
        this.content = content;

        // add the new content to the stack pane
        contentContainer.getChildren().remove(1);
        contentContainer.getChildren().add(content);

        VBox.setVgrow(content, javafx.scene.layout.Priority.ALWAYS);

    }

    public void setTitle(String title) {
        header.setTitle(title);
    }

    public void setOnBackClickedCallback(Runnable callback) {
        header.backButtonContainer.setOnBackClickedCallback(callback);
    }

    public void showBackButton() {
        header.showBackButton();
    }
    
    public void hideBackButton() {
        header.hideBackButton();
    }    

    class FabContainer extends GridPane {
        public FabContainer() {
            super();
            this.setAlignment(Pos.BOTTOM_RIGHT); // Align the FAB to the bottom right
            this.setPadding(new Insets(0, 24, 8, 0));
            // enable click only in the fab, not in the container
            this.setPickOnBounds(false);
        }

        public void setFab(FloatingActionButton fab) {
            // Remove the previous fab
            this.getChildren().clear();
            this.getChildren().add(fab);
            this.setAlignment(Pos.BOTTOM_RIGHT);
        }

        public FloatingActionButton getFab() {
            if (!this.getChildren().isEmpty()) {
                return (FloatingActionButton) this.getChildren().get(0);
            } else {
                // Handle the case where there are no children (return null or throw an
                // exception)
                return null; // Change this according to your needs
            }
        }

    }

    class Header extends HBox {
        private BackButtonContainer backButtonContainer = new BackButtonContainer();
        private Label titleLabel = new Label(); // Add Label for title

        public Header() {
            super();

            titleLabel.getStyleClass().add("title");

            this.setAlignment(Pos.CENTER_LEFT);
            this.setPadding(new Insets(0));
            this.setPickOnBounds(false);
            this.getChildren().addAll(backButtonContainer, titleLabel); // Add titleLabel to the Header
            this.getStyleClass().add("header");
        }

        public void setTitle(String title) {
            titleLabel.setText(title);
        }

        public String getTitle() {
            return titleLabel.getText();
        }

        public void showBackButton() {
            backButtonContainer.show();
        }

        public void hideBackButton() {
            backButtonContainer.hide();
        }
    }

    class BackButtonContainer extends HBox {
        private MFXButton backButton = createBackButton();
        private Runnable onBackClickedCallback;

        public BackButtonContainer() {
            super();
            this.setAlignment(Pos.CENTER_LEFT);
            this.setPadding(new Insets(10));
            this.setPickOnBounds(false);
            hide(); // Initially hide the back button
        }

        public void show() {
            if (!this.getChildren().contains(backButton)) {
                this.getChildren().add(backButton);
            }
        }
        

        public void hide() {
            if (this.getChildren().contains(backButton)) {
                this.getChildren().remove(backButton);
            }
        }

        public void setOnBackClickedCallback(Runnable callback) {
            this.onBackClickedCallback = callback;
        }

        private MFXButton createBackButton() {
            SVGWidget backButtonIcon = new SVGWidget("/hitbeat/svg/back-button.svg", 30, Color.WHITE);

            MFXButton backButton = new MFXButton("");
            backButton.setGraphic(backButtonIcon);
            backButton.setStyle("-fx-background-color: transparent;");
            StackPane.setAlignment(backButton, Pos.TOP_LEFT);
            StackPane.setMargin(backButton, new Insets(10));

            backButton.setOnAction(e -> {
                if (onBackClickedCallback != null) {
                    onBackClickedCallback.run();
                }
            });

            return backButton;
        }
    }

}
