package hitbeat.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="PlaylistTrack")
public class PlaylistTrack extends BaseModel{    

    @OneToMany(mappedBy = "playlistTrack")
    private Set<Track> tracks = new HashSet<>();

    @OneToMany(mappedBy = "playlistTrack")
    private Set<Playlist> playlists = new HashSet<>();

    private int index;

}
