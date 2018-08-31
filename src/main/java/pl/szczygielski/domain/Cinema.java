package pl.szczygielski.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "cinema")
    @JsonIgnore
    private List<Seance> seances;

}
