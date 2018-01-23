package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.SeanceRepository;

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
        return seanceRepository.findOne(id);
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
        List<Seance> seancesIncity = allSeances.stream().filter(a -> a.getCinema().getCity().equals(city)).collect(Collectors.toList());
        return seancesIncity;
    }
}
