package pl.szczygielski.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private String filmGenre;
    @OneToMany(mappedBy = "movie")
    private List<Seance> seances;
}
