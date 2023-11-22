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
    protected Image getImageGrid(List<Image> images, double size) {
        if (images.isEmpty()) {
            return new Image("/hitbeat/images/default.png");
        }

        // Limit the number of images to 4
        int numImages = Math.min(images.size(), 4);

        // Calculate the scaling factor for each image
        double scale = size / Math.max(images.get(0).getWidth(), images.get(0).getHeight());

        // Create a WritableImage for the grid
        int gridSize = (int) Math.round(Math.sqrt(numImages) * size);
        WritableImage gridImage = new WritableImage(gridSize, gridSize);
        PixelWriter writer = gridImage.getPixelWriter();

        for (int i = 0; i < numImages; i++) {
            Image img = images.get(i);
            if (img != null) {
                double x = (i % 2) * size; // Column: 0 or 1
                double y = (i / 2) * size; // Row: 0 or 1
                PixelReader reader = img.getPixelReader();

                for (int readY = 0; readY < size; readY++) {
                    for (int readX = 0; readX < size; readX++) {
                        // Calculate the corresponding position in the original image
                        int origX = (int) (readX / scale);
                        int origY = (int) (readY / scale);

                        // Ensure origX and origY are within bounds
                        if (origX >= 0 && origX < img.getWidth() && origY >= 0 && origY < img.getHeight()) {
                            Color color = reader.getColor(origX, origY);
                            writer.setColor((int) (x + readX), (int) (y + readY), color);
                        }
                    }
                }

            }
        }

        return gridImage;
    }

}
