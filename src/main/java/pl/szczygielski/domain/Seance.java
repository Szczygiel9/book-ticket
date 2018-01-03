package pl.szczygielski.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "seances")
@Data
public class Seance implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seance")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    private String type;
    private String hour;
    private String day;
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
}
