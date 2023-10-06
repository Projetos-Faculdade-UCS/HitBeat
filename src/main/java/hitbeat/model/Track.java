package hitbeat.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
public class Track extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date creationDate;
    private int duration;
    private String picturePath;
    private String filePath;
    private boolean explicit;
    private boolean single;
    private boolean favorite;

    @ManyToOne(optional = false)
    @JoinColumn(name = "genre_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Genre genre;

    @ManyToOne(optional = true)
    @JoinColumn(name = "artist_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Artist artist;

    @ManyToMany(mappedBy = "tracks")
    private Set<Queue> queues = new HashSet<>();

    public Track() {
    }

    public Track(Long id, String name, Date creationDate, int duration, String picturePath, String filePath,
            boolean explicit, boolean single, boolean favorite, Genre genre) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.duration = duration;
        this.picturePath = picturePath;
        this.filePath = filePath;
        this.explicit = explicit;
        this.single = single;
        this.genre = genre;
    }

    // Track(Long, String, Date, int, String, String, boolean, boolean, boolean, Genre, Set<Queue>)
    public Track(Long id, String name, Date creationDate, int duration, String picturePath, String filePath,
            boolean explicit, boolean single, boolean favorite, Genre genre, Artist artist, Set<Queue> queues) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.duration = duration;
        this.picturePath = picturePath;
        this.filePath = filePath;
        this.explicit = explicit;
        this.single = single;
        this.genre = genre;
        this.queues = queues;
        this.artist = artist;
    }

    public Track(String name, Date creationDate, int duration, String picturePath, String filePath,
            boolean explicit, boolean single, boolean favorite, Genre genre) {
        this.name = name;
        this.creationDate = creationDate;
        this.duration = duration;
        this.picturePath = picturePath;
        this.filePath = filePath;
        this.explicit = explicit;
        this.single = single;
        this.genre = genre;
    }

}
