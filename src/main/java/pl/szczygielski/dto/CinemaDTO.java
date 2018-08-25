package pl.szczygielski.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CinemaDTO {

    private Long id;
    private String city;
    private String address;
}
