package pl.szczygielski.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SeanceDTO {

    private Long id;
    private String type;
    private String hour;
    private String day;
    private Integer freeSeats;
    private Integer reservedSeats;
    private CinemaDTO cinema;
    private List<ReservationDTO> reservations;
}
