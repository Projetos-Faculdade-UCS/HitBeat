package hitbeat.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javafx.scene.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="Playlist")
public class Playlist extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy="playlist")
    private Set<PlaylistTrack> playlistTracks = new HashSet<>();

    @Column(name = "pub_date")
    private Date pubDate;

    private String name;

    private String description;

    @Column(name = "file_path")
    private String filePath;

    public Image getCover() {
        if (this.filePath == null) {
            return new Image("/hitbeat/images/track.jpg");
        }
        return new Image(this.filePath);
    }
}
