package pl.szczygielski.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String director;
    private String filmGenre;
    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private List<Seance> seances;
}
