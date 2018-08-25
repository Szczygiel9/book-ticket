package pl.szczygielski.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReservationDTO {

    private Long id;
    private Integer name;
}
