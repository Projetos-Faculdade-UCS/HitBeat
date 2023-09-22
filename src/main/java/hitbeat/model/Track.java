package hitbeat.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Date creationDate;
    private int duration;
    private String picturePath;
    private String filePath;
    private boolean explicit;
    private boolean single;
    private boolean favorite;

    @OneToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Track() {
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean getFavorite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void toggleFavorite() {
        this.favorite = !this.favorite;
    }

}
