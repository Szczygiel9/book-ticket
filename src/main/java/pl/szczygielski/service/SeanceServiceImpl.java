package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.SeanceDTO;
import pl.szczygielski.mapper.SeanceMapper;
import pl.szczygielski.repository.SeanceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeanceServiceImpl implements SeanceService {

    private SeanceRepository seanceRepository;

    private SeanceMapper seanceMapper;

    @Autowired
    public SeanceServiceImpl(SeanceRepository seanceRepository, final SeanceMapper seanceMapper) {
        this.seanceRepository = seanceRepository;
        this.seanceMapper = seanceMapper;
    }

    @Override
    public Seance saveSeance(Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public SeanceDTO getOne(Long id) {
        final Seance seance = seanceRepository.findOne(id);
        if (seance == null) {
            throw new EntityNotFoundException();
        }
        return seanceMapper.mapSeanceToSeanceDTO(seance);
    }

    @Override
    public List<SeanceDTO> returnAll() {
        final List<Seance> allSeances = seanceRepository.findAll();
        return seanceMapper.mapSeancesToDTO(allSeances);
    }

    @Override
    public Long countQuantity() {
        return seanceRepository.count();
    }

    @Override
    public List<SeanceDTO> searchSeancesByCity(String city) {
        List<Seance> allSeances = seanceRepository.findAll();
        final List<Seance> seancesByCity = allSeances.stream().filter(a -> a.getCinema().getCity().equals(city)).collect(Collectors.toList());
        return seanceMapper.mapSeancesToDTO(seancesByCity);
    }
}
