package pl.szczygielski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.CinemaDTO;
import pl.szczygielski.mapper.CinemaMapper;
import pl.szczygielski.repository.CinemaRepository;
import pl.szczygielski.repository.SeanceRepository;
import pl.szczygielski.service.CinemaService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class CinemaServiceImpl implements CinemaService {

    private SeanceRepository seanceRepository;
    private CinemaRepository cinemaRepository;
    private CinemaMapper cinemaMapper;

    @Autowired
    public CinemaServiceImpl(final SeanceRepository seanceRepository, final CinemaRepository cinemaRepository, final CinemaMapper cinemaMapper) {
        this.seanceRepository = seanceRepository;
        this.cinemaRepository = cinemaRepository;
        this.cinemaMapper = cinemaMapper;
    }

    public Cinema saveCinema(final Cinema cinema) {
        return cinemaRepository.save(cinema);
    }

    @Override
    public CinemaDTO getCinemaById(final Long id) {
        final Cinema cinema = cinemaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return cinemaMapper.mapCinemaToCinemaDTO(cinema);
    }

    @Override
    public List<CinemaDTO> getAllCinemas() {
        return cinemaMapper.mapCinemasToCinemasListDTO(cinemaRepository.findAll());
    }

    @Override
    public Long countQuantity() {
        return cinemaRepository.count();
    }

    public void addSeanceToCinema(final Long cinemaId, final Long seanceId) {
        final Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(EntityNotFoundException::new);
        final Seance seance = seanceRepository.findById(seanceId).orElseThrow(EntityNotFoundException::new);

        cinema.getSeances().add(seance);
        cinemaRepository.save(cinema);
    }

    public List<String> getCitesWhereMoviesArePlayed() {
        return cinemaRepository.getCitiesWhereMoviesArePlayed();
    }
}
