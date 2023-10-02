package hitbeat.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "track")
public class Track extends BaseModel {
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

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Track() {
    }

    public Track(String name, Date creationDate, int duration, String picturePath, String filePath, boolean explicit,
            boolean single, Genre genre) {
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
