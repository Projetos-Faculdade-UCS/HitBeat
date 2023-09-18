package hitbeat.view.footer;

import org.apache.batik.transcoder.SVGAbstractTranscoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import org.apache.batik.transcoder.image.ImageTranscoder;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import javafx.fxml.FXML;
import javafx.scene.image.Image;


public class Svg2Image {
    
    public static Image convert(InputStream svgInputStream) {
        try{
            return convertSvgToImage(svgInputStream);
        }catch(Exception e){
           return null;
        }
        
    }

    @FXML
    private static Image convertSvgToImage(InputStream svgInputStream) {
        try {
            ByteArrayOutputStream resultByteStream = new ByteArrayOutputStream();

            TranscoderInput transcoderInput = new TranscoderInput("https://icons.getbootstrap.com/assets/icons/1-square.svg");
            TranscoderOutput transcoderOutput = new TranscoderOutput(resultByteStream);
            
            PNGTranscoder pngTranscoder = new PNGTranscoder();
            pngTranscoder.addTranscodingHint(SVGAbstractTranscoder.KEY_HEIGHT, 256f);
            pngTranscoder.addTranscodingHint(SVGAbstractTranscoder.KEY_WIDTH, 256f);
            pngTranscoder.transcode(transcoderInput, transcoderOutput);
            // resultByteStream.flush();

            // InputStream imageInputStream = new ByteArrayInputStream(resultByteStream.toByteArray());
            // return new Image(imageInputStream);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
