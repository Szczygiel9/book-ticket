package pl.szczygielski.mapper;

import pl.szczygielski.domain.Cinema;
import pl.szczygielski.dto.CinemaDTO;

import java.util.List;

public interface CinemaMapper {

    CinemaDTO mapCinemaToCinemaDTO(final Cinema cinema);

    List<CinemaDTO> mapCinemasToCinemasListDTO(final List<Cinema> cinemas);
}
