package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.domain.Seance;

import java.util.List;

@Service
public interface ReservationService {
    Reservation getOne(Long id);
    List<Reservation> returnAll();
    Long countQuantity ();
    Reservation saveReservation(Reservation reservation);
    Reservation reservateSeat(Seance seance);
}
