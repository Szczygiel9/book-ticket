package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.dto.CinemaDTO;

import java.util.List;

@Service
public interface CinemaService {
    Cinema saveCinema(Cinema cinema);

    CinemaDTO getCinemaById(Long id);

    List<CinemaDTO> getAllCinemas();
    Long countQuantity();
    void addSeanceToCinema(Long cinemaId, Long seanceId);

    List<String> getCitesWhereMoviesArePlayed();
}
