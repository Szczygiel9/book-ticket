package pl.szczygielski.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import pl.szczygielski.domain.SeanceType;
import pl.szczygielski.utils.MediumDateSerializer;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class SeanceDTO {

    private Long id;
    private SeanceType type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonSerialize(using = MediumDateSerializer.class)
    private Date seanceDate;
    private Integer freeSeats;
    private Integer reservedSeats;
    private CinemaDTO cinema;
    private List<ReservationDTO> reservations;
}
