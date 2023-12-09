package hitbeat.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import io.github.palexdev.mfxcore.utils.fx.SwingFXUtils;
import javafx.scene.image.Image;

public class Image2Bytes {
    public static byte[] convertImageToBytes(Image image) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }
}
