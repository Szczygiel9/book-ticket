package pl.szczygielski.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    @Enumerated(EnumType.STRING)
    private SeanceType type;
    @Basic
    private Date seanceDate;
    private Integer freeSeats;
    private Integer reservedSeats;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @OneToMany(mappedBy = "seance")
    private List<Reservation> reservations;

}
