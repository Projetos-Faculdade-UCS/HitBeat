package hitbeat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "genre", uniqueConstraints = {
        @jakarta.persistence.UniqueConstraint(columnNames = "name")
})
public class Genre extends BaseModel {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }
}
