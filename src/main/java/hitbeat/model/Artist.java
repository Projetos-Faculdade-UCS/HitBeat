package hitbeat.model;

import hitbeat.util.HibernateUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import javafx.scene.image.Image;
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
// named query to get the first album of an artist:
// SELECT Album.*
// FROM Album
// JOIN track ON Album.id = track.album_id
// JOIN artist ON track.artist_id = artist.id
// WHERE artist.name = 'ArtistName' -- replace 'ArtistName' with the actual artist's name
// ORDER BY Album.launch_date ASC
@NamedQuery(
    name = "Artist.getFirstAlbum", 
    query = "SELECT a FROM Album a JOIN a.tracks t WHERE t.artist = :artist ORDER BY a.launchDate ASC"
)
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

    public Image getCover() {
        if (this.image != null) {
            return new Image(this.image);
        } else{
            // get the first album of the artist
            Album album = (Album) this.getEntityManager().createNamedQuery("Artist.getFirstAlbum")
                    .setParameter("artist", this)
                    .setMaxResults(1)
                    .getSingleResult();

            if (album != null) {
                return album.getCover();
            }
        }
        return new Image("/hitbeat/images/artists/artist.jpg");
    }

    public EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager();
    }
}
