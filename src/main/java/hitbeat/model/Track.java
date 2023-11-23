package hitbeat.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import javafx.scene.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

@With
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "track", uniqueConstraints = {
        @UniqueConstraint(columnNames = "filePath", name = "track_filePath_unique")
})
@NamedQueries({
    @NamedQuery(name = "Track.findByAlbumArtist", query = "SELECT t FROM Track t WHERE t.album.artist = :artist"),
    @NamedQuery(name = "Track.getFavorites", query = "select t from Track t where t.favorite = true")
})
public class Track extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date creationDate;
    private int duration;

    private String filePath;
    private boolean explicit;
    private boolean single;
    private boolean favorite;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Genre genre;

    @ManyToOne(optional = true)
    @JoinColumn(name = "album_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Album album;

    @OneToMany(mappedBy = "track")
    private Set<PlaylistTrack> playlistTrack = new HashSet<>();

    public Track() {
    }

    public Track(Long id, String name, Date creationDate, int duration, String filePath,
            boolean explicit, boolean single, boolean favorite, Genre genre) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.duration = duration;
        this.filePath = filePath;
        this.explicit = explicit;
        this.single = single;
        this.genre = genre;
    }

    public Track(Long id, String name, Date creationDate, int duration, String filePath,
            boolean explicit, boolean single, boolean favorite, Genre genre, Album album,
            Set<PlaylistTrack> playlistTrack) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.duration = duration;
        this.filePath = filePath;
        this.explicit = explicit;
        this.single = single;
        this.genre = genre;
        this.album = album;
        this.playlistTrack = playlistTrack;
    }

    public Track(String name, Date creationDate, int duration, String filePath,
            boolean explicit, boolean single, boolean favorite, Genre genre) {
        this.name = name;
        this.creationDate = creationDate;
        this.duration = duration;
        this.filePath = filePath;
        this.explicit = explicit;
        this.single = single;
        this.genre = genre;
    }

    public Image getCover() {
        if (this.album == null || this.album.getCover() == null) {
            return new Image("/hitbeat/images/default.png");
        }
        return this.album.getCover();
    }

}
