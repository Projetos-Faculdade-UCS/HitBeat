package hitbeat.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="Playlist")
public class Playlist extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy="playlist")
    private Set<PlaylistTrack> playlistTracks = new HashSet<>();

    @Column(name = "pub_date")
    private Date pubDate;

    private String name;

    private String description;

    @Column(name = "file_path")
    private String filePath;

    public Image getCover() {
        if (this.filePath == null) {
            return new Image("/hitbeat/images/track.jpg");
        }
        return new Image(this.filePath);
    }
    
    public Playlist() {
    }

    public Playlist(String name) {
        this.name = name;
    }

    public Playlist(String name, String description, String filePath, Date pubDate, Set<PlaylistTrack> playlistTracks) {
        this.name = name;
        this.description = description;
        this.filePath = filePath;
        this.pubDate = pubDate;
        this.playlistTracks = playlistTracks;
    }

    public Playlist(Long id, Set<PlaylistTrack> playlistTracks, Date pubDate, String name, String description, String filePath){
        this.id = id;
        this.playlistTracks = playlistTracks;
        this.pubDate = pubDate;
        this.name = name;
        this.description = description;
        this.filePath = filePath;
    }

    public List<Track> getTracks() {
        List<Track> tracks = new ArrayList<>();

        for (PlaylistTrack playlistTrack : playlistTracks) {
            tracks.add(playlistTrack.getTrack());
        }

        return tracks;
    }
}