package pl.szczygielski.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Movie movie;
    private String type;
    private String hour;
    private String day;
    private int freeSeats;
    private int reservatedSeats;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @OneToMany(mappedBy = "seance")
    @JsonIgnore
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
