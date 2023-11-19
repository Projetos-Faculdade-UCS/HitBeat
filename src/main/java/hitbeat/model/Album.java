package hitbeat.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javafx.scene.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Album")
@NamedQueries({
        @NamedQuery(name = "Album.getByName", query = "select a from Album a where a.name = :name"),
        @NamedQuery(name = "Album.findByName", query = "select a from Album a where a.name in :names")
})
public class Album extends BaseModel {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "launch_date")
    private Date launchDate;

    @OneToMany(mappedBy = "album")
    private Set<Track> tracks = new HashSet<>();

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] cover;

    public Image getCover() {
        if (this.cover == null) {
            return new Image("/hitbeat/images/track.jpg");
        }
        InputStream inputStream = new ByteArrayInputStream(this.cover);
        return new Image(inputStream);
    }
}
