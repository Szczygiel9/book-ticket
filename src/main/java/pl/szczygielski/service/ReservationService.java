package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.SeanceRepository;

@Service
public class ReservationService {

    private SeanceRepository seanceRepository;

    @Autowired
    public ReservationService(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    public void reservateSeat(Seance seance, Reservation reservation){
        seanceRepository.findById(seance.getId()).getReservations().add(reservation);
    }
}
