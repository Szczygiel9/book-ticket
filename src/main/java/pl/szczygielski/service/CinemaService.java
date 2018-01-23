package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Cinema;

import java.util.List;

@Service
public interface CinemaService {
    Cinema saveCinema(Cinema cinema);
    Cinema getOne(Long id);
    List<Cinema> returnAll();
    Long countQuantity();
    void addSeanceToCinema(Long cinemaId, Long seanceId);
    List<String> searchCities();
}
