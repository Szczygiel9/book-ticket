package pl.szczygielski.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cinemas")
@Data
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cinema")
    private Long id;
    private String city;
    private String address;
    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER)
    private List<Seance> seances;
}
