package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.dto.ReservationDTO;

import java.util.List;

@Service
public interface ReservationService {
    ReservationDTO getOne(Long id);

    List<ReservationDTO> returnAll();

    Long countQuantity();

    Reservation saveReservation(Reservation reservation);

    Reservation reservateSeat(Long seance);
}
