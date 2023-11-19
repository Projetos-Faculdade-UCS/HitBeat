package hitbeat.view.base.utils;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class MyButton extends Button{

    public MyButton(){
        super();
        initUI();
    }

    public MyButton(String text){
        super(text);
        initUI();
    }

    public MyButton(String text, Node graphic){
        super(text, graphic);
        initUI();
    }

    private void initUI() {
        this.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");
    }
}
