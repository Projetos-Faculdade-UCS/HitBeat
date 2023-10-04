package hitbeat.view;

import javafx.beans.property.ReadOnlyDoubleProperty;
import lombok.Getter;
import lombok.Setter;

// a singleton class that holds the layout of the application
@Getter
@Setter
public class Layout {
    private static Layout instance = null;

    private Layout() {
    }

    public static Layout getInstance() {
        if (instance == null) {
            instance = new Layout();
        }
        return instance;
    }
    
    // the width and height of the content
    private ReadOnlyDoubleProperty contentWidth;
    private ReadOnlyDoubleProperty contentHeight;

}
