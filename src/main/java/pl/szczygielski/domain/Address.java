package pl.szczygielski.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Address implements Serializable {

    private String city;
    private String street;
    private String number;

}
