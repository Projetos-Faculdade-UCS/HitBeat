package hitbeat.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.function.Function;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

import lombok.Setter;

@Setter
public class CustomMP3File {

    private final Mp3File mp3file;
    private String title;
    private String album;
    private String artist;
    private String genre;
    private String year;
    private byte[] image;

    public CustomMP3File(File file) throws IOException, UnsupportedTagException, InvalidDataException, NotSupportedException {
        this.mp3file = new Mp3File(file);
    }

    public String getTitle() {
        return title != null ? title : (title = extractData(ID3v2::getTitle, ID3v1::getTitle));
    }

    public String getAlbum() {
        return album != null ? album : (album = extractData(ID3v2::getAlbum, ID3v1::getAlbum));
    }

    public String getArtist() {
        return artist != null ? artist : (artist = extractData(ID3v2::getArtist, ID3v1::getArtist));
    }

    public String getGenre() {
        return genre != null ? genre : (genre = extractData(ID3v2::getGenreDescription, ID3v1::getGenreDescription));
    }

    public String getFilePath() {
        return mp3file.getFilename();
    }

    public byte[] getImage() {
        if (image == null && mp3file.hasId3v2Tag()) {
            image = mp3file.getId3v2Tag().getAlbumImage();
        }
        return image;
    }

    public String getYear() {
        return year != null ? year : (year = extractData(ID3v2::getYear, ID3v1::getYear));
    }

    private String extractData(Function<ID3v2, String> v2Function, Function<ID3v1, String> v1Function) {
        if (mp3file.hasId3v2Tag()) {
            String data = v2Function.apply(mp3file.getId3v2Tag());
            if (data != null && !data.isEmpty()) {
                return decodeString(data);
            }
        }
        if (mp3file.hasId3v1Tag()) {
            String data = v1Function.apply(mp3file.getId3v1Tag());
            if (data != null && !data.isEmpty()) {
                return decodeString(data);
            }
        }
        return null;
    }

    private String decodeString(String input) {
        Charset[] charsetsToBeTested = {
                Charset.forName("UTF-8"),
                Charset.forName("ISO-8859-1"),
                Charset.forName("windows-1252"),
                Charset.forName("UTF-16BE"),
                Charset.forName("UTF-16LE"),
                Charset.forName("UTF-32BE"),
                Charset.forName("UTF-32LE")
        };

        for (Charset charset : charsetsToBeTested) {
            try {
                String decodedString = new String(input.getBytes("ISO-8859-1"), charset);

                return decodedString;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return input;
    }

    public String getFilenameAsUri() {
        File file = new File( mp3file.getFilename() );
        return file.toURI().toString();
    }
}
