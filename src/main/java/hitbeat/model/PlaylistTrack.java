package hitbeat.model;


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

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="Playlist_Track")
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

}
