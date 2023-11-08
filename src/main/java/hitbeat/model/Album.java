package hitbeat.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javafx.scene.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="Album")
public class Album extends BaseModel{
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    private Date dt_lancamento;

    private String filePath;

    @OneToMany(mappedBy = "album")
    private Set<Track> tracks = new HashSet<>();

    public Image getCover() {
        if (this.filePath == null) {
            return new Image("/hitbeat/images/track.jpg");
        }
        return new Image(this.filePath);
    }
}
