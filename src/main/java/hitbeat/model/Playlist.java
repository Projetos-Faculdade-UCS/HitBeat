package hitbeat.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.NamedQuery;

import hitbeat.util.HibernateUtil;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javafx.scene.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

@With
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Playlist")
@NamedQuery(name = "Playlist.getTracks", query = "SELECT pt.track FROM PlaylistTrack pt WHERE pt.playlist = :playlist")
public class Playlist extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "playlist")
    private Set<PlaylistTrack> playlistTracks;

    @Column(name = "pub_date")
    private Date pubDate;

    private String name;

    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] cover;

    public Image getCover(double size) {
        if (this.cover != null) {
            InputStream inputStream = new ByteArrayInputStream(this.cover);
            return new Image(inputStream);
        }
        // get all tracks from the playlist
        List<Track> tracks = HibernateUtil.getEntityManager().createNamedQuery("Playlist.getTracks", Track.class)
                .setParameter("playlist", this)
                .setMaxResults(5)
                .getResultList();

        if (tracks.size() >= 4) {
            List<Image> images = tracks.stream().map(Track::getCover).toList();
            return getImageGrid(images, size);
        } else if (tracks.size() > 0) {
            return tracks.get(0).getCover();
        }
        return new Image("/hitbeat/images/default.png");
    }

    public Playlist() {
    }

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist(String name, String description, byte[] cover, Date pubDate, Set<PlaylistTrack> playlistTracks) {
        this.name = name;
        this.description = description;
        this.cover = cover;
        this.pubDate = pubDate;
        this.playlistTracks = playlistTracks;
    }

    public Playlist(Long id, Set<PlaylistTrack> playlistTracks, Date pubDate, String name, String description,
            byte[] cover) {
        this.id = id;
        this.playlistTracks = playlistTracks;
        this.pubDate = pubDate;
        this.name = name;
        this.description = description;
        this.cover = cover;
    }

}