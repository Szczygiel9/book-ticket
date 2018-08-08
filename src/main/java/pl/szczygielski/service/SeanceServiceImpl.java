package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.SeanceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeanceServiceImpl implements SeanceService{

    private SeanceRepository seanceRepository;

    @Autowired
    public SeanceServiceImpl(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    @Override
    public Seance saveSeance(Seance seance) {
        return seanceRepository.save(seance);
    }

    @Override
    public Seance getOne(Long id) {
        final Seance seance = seanceRepository.findOne(id);
        if (seance == null) {
            throw new EntityNotFoundException();
        }
        return seance;
    }

    @Override
    public List<Seance> returnAll() {
        return seanceRepository.findAll();
    }

    @Override
    public Long countQuantity() {
        return seanceRepository.count();
    }

    @Override
    public List<Seance> searchSeancesByCity(String city) {
        List<Seance> allSeances = seanceRepository.findAll();
        return allSeances.stream().filter(a -> a.getCinema().getCity().equals(city)).collect(Collectors.toList());
    }
}
