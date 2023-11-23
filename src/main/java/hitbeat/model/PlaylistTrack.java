package hitbeat.model;


import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

@With
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="Playlist_Track")
@NamedQueries({
     @NamedQuery(name = "PlaylistTrack.delete", query = "DELETE FROM PlaylistTrack pt WHERE pt.playlist = :playlist AND pt.track = :track"),
})
public class PlaylistTrack extends BaseModel{    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "track_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Track track;

    @ManyToOne(optional = false)
    @JoinColumn(name = "playlist_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Playlist playlist;

    private int index;

    public PlaylistTrack() {
    }

    public PlaylistTrack(Playlist playlist, Track track) {
        this.playlist = playlist;
        this.track = track;
    }

    public PlaylistTrack(Long id, Track track, Playlist playlist, int index) {
        this.playlist = playlist;
        this.track = track;
        this.index = index;
        this.id = id;
    }

}
