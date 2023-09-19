package hitbeat.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;
    private Date dt_criacao;
    private int duracao; // em segundos
    private String path_foto;
    private String path_musica;
    private boolean explicit;
    private boolean single;
    private boolean favorito;

}
