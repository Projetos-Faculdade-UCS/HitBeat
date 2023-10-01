package hitbeat.view.base.widgets;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class SVGWidget extends StackPane {

    private String resourcePath;
    private double width;
    private double height;
    private Color color;

    public SVGWidget(String resourcePath) {
        this.resourcePath = resourcePath;
        this.width = 0;
        this.height = 0;
        this.color = Color.WHITE;
        this.build();
    }

    public SVGWidget(String resourcePath, Color color) {
        this.resourcePath = resourcePath;
        this.width = 30;
        this.height = 30;
        this.color = color;
        this.build();
    }

    public SVGWidget(String resourcePath, double size) {
        this.resourcePath = resourcePath;
        this.width = size;
        this.height = size;
        this.color = Color.WHITE;
        this.build();
    }

    public SVGWidget(String resourcePath, double size, Color color) {
        this.resourcePath = resourcePath;
        this.width = size;
        this.height = size;
        this.color = color;
        this.build();
    }

    public SVGWidget(String resourcePath, double width, double height) {
        this.resourcePath = resourcePath;
        this.width = width;
        this.height = height;
        this.color = Color.WHITE;
        this.build();
    }

    public SVGWidget(String resourcePath, double width, double height, Color color) {
        this.resourcePath = resourcePath;
        this.width = width;
        this.height = height;
        this.color = color;
        this.build();
    }

    public void build() {
        ArrayList<String> paths = getSvgPaths();

        this.setPrefSize(width, height);
        this.setMaxSize(width, height);
        this.setMinSize(width, height);

        for (String path : paths) {
            SVGPath svgPath = new SVGPath();
            svgPath.setContent(path);

            double scaleX = width / svgPath.getBoundsInLocal().getWidth();
            double scaleY = height / svgPath.getBoundsInLocal().getHeight();

            svgPath.setScaleX(scaleX);
            svgPath.setScaleY(scaleY);

            svgPath.setFill(color);
            this.getChildren().add(svgPath);
        }

    }

    private Document getDocument() {
        try {
            InputStream inputStream = getClass().getResourceAsStream(resourcePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<String> getSvgPaths() {
        Document doc = getDocument();
        NodeList pathsNodeList = doc.getElementsByTagName("path");
        ArrayList<String> paths = new ArrayList<String>();
        for (int i = 0; i < pathsNodeList.getLength(); i++) {
            paths.add(pathsNodeList.item(i).getAttributes().getNamedItem("d").getNodeValue());
        }
        return paths;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}