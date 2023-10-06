package hitbeat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

@Data
@With
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "artist", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "artist_name_unique")
})
public class Artist extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String image;

    public Artist() {
    }

    public Artist(Long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
