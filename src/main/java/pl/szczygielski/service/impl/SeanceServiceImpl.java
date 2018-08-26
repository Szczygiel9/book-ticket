package pl.szczygielski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.SeanceDTO;
import pl.szczygielski.mapper.SeanceMapper;
import pl.szczygielski.repository.SeanceRepository;
import pl.szczygielski.service.SeanceService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class SeanceServiceImpl implements SeanceService {

    private SeanceRepository seanceRepository;

    private SeanceMapper seanceMapper;

    @Autowired
    public SeanceServiceImpl(final SeanceRepository seanceRepository, final SeanceMapper seanceMapper) {
        this.seanceRepository = seanceRepository;
        this.seanceMapper = seanceMapper;
    }

    @Override
    public Seance saveSeance(final Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public SeanceDTO getSeanceById(final Long id) {
        final Seance seance = seanceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return seanceMapper.mapSeanceToSeanceDTO(seance);
    }

    @Override
    public List<SeanceDTO> getAllSeances() {
        final List<Seance> allSeances = seanceRepository.findAll();
        return seanceMapper.mapSeancesToDTO(allSeances);
    }

    @Override
    public Long countQuantity() {
        return seanceRepository.count();
    }

    @Override
    public List<SeanceDTO> getSeancesInCity(final String city) {
        final List<Seance> seancesByCity = seanceRepository.getSeancesByCinemaCity(city);
        return seanceMapper.mapSeancesToDTO(seancesByCity);
    }
}
