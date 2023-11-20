package hitbeat.model;

import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import hitbeat.util.HibernateUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@NamedQueries({
        @NamedQuery(name = "Artist.getAlbums", query = "SELECT a FROM Album a WHERE a.artist = :artist")
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

    public Image getCover(double size) {
        if (this.image != null) {
            return new Image(this.image);
        } else {
            // get the first album of the artist
            List<Album> album = this.getEntityManager().createNamedQuery("Artist.getAlbums", Album.class)
                    .setParameter("artist", this)
                    .setMaxResults(4)
                    .getResultList();

            if (album.size() >= 4) {
                List<Image> images = album.stream().map(Album::getCover).toList();
                return getImageGrid(images, size);
            } else if (album.size() > 0) {
                return album.get(0).getCover();
            }
        }
        return new Image("/hitbeat/images/default.png");
    }

    public EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager();
    }
}
