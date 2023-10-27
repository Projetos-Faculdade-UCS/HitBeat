package hitbeat.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import javafx.scene.image.Image;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="playlist")
public class Playlist extends Queue{
    
    private Date creationDate;
    private String title;
    private String description;
    private String picture;

    public Image getCover(){
           if (this.picture == null) {
            return new Image("/hitbeat/images/track.jpg");
        }
        return new Image(this.picture);
    }
}
