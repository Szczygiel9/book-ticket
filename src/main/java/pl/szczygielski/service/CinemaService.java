package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.CinemaRepository;
import pl.szczygielski.repository.SeanceRepository;

@Service
public class CinemaService {

    private SeanceRepository seanceRepository;
    private CinemaRepository cinemaRepository;

    @Autowired
    public CinemaService(SeanceRepository seanceRepository, CinemaRepository cinemaRepository) {
        this.seanceRepository = seanceRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public void addSeanceToCinema(Long cinemaId, Long seanceId){
        Cinema cinema = cinemaRepository.findById(cinemaId);
        Seance seance = seanceRepository.findById(seanceId);

        cinema.getSeances().add(seance);
        cinemaRepository.save(cinema);
    }
}
