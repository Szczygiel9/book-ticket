package pl.szczygielski.dto;

import lombok.Builder;
import lombok.Getter;
import pl.szczygielski.domain.Address;

@Builder
@Getter
public class CinemaDTO {

    private Long id;
    private String name;
    private Address address;
}
