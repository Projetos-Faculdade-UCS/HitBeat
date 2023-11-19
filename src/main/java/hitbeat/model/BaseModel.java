package hitbeat.model;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class BaseModel {
    protected Image getImageGrid(List<Image> images) {
        if (images.isEmpty()) {
            return new Image("/hitbeat/images/artists/artist.jpg");
        }
        // only 4 images are allowed
        images = images.subList(0, Math.min(images.size(), 4));

        int imageSize = 100; // Set this to the actual size of your images
        int gridSize = 2 * imageSize; // For a 2x2 grid
        WritableImage gridImage = new WritableImage(gridSize, gridSize);
        PixelWriter writer = gridImage.getPixelWriter();

        for (int i = 0; i < images.size(); i++) {
            Image img = images.get(i);
            if (img != null) {
                int x = (i % 2) * imageSize; // Column: 0 or 1
                int y = (i / 2) * imageSize; // Row: 0 or 1
                PixelReader reader = img.getPixelReader();
        
                for (int readY = 0; readY < img.getHeight() && readY < imageSize; readY++) {
                    for (int readX = 0; readX < img.getWidth() && readX < imageSize; readX++) {
                        Color color = reader.getColor(readX, readY);
                        writer.setColor(x + readX, y + readY, color);
                    }
                }
            }
        }
        

        return gridImage;
    }
    
}
