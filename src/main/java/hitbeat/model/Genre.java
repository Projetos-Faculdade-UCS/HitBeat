package hitbeat.model;

import java.util.List;

import org.hibernate.annotations.NamedQuery;

import hitbeat.util.HibernateUtil;
import jakarta.persistence.Entity;
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
@Table(name = "genre", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name", name = "genre_name_unique")
})
// named query to get all albums from a genre (the genre is related to the
// track, and the track is related to the album)
@NamedQuery(name = "Genre.getAlbums", query = "SELECT t.album FROM Track t WHERE t.genre = :genre")
public class Genre extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Image getCover(double size) {
        List<Album> albums = HibernateUtil.getEntityManager().createNamedQuery("Genre.getAlbums", Album.class)
                .setParameter("genre", this)
                .getResultList();

        // remove duplicates
        // albums = albums.stream().distinct().toList();

        if (albums.size() >= 4) {
            List<Image> images = albums.stream().map(Album::getCover).toList();
            return getImageGrid(images, size);
        } else if (albums.size() > 0) {
            return albums.get(0).getCover();
        }
        return new Image("/hitbeat/images/default.png");
    }
}
