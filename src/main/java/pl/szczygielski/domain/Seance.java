package pl.szczygielski.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "seances")
@Data
public class Seance implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seance")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;
    private String type;
    private String hour;
    private String day;
    private Integer freeSeats;
    private Integer reservedSeats;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @OneToMany(mappedBy = "seance")
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", movie=" + movie.getTitle() + movie.getDirector() + movie.getFilmGenre() +
                ", type='" + type + '\'' +
                ", hour='" + hour + '\'' +
                ", day='" + day + '\'' +
                ", cinema=" + cinema.getAddress() + cinema.getCity() +
                '}';
    }
}
