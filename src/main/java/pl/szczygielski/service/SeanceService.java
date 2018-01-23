package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Seance;

import java.util.List;

@Service
public interface SeanceService {
    Seance saveSeance(Seance seance);
    Seance getOne(Long id);
    List<Seance> returnAll();
    Long countQuantity();
    List<Seance> searchSeancesByCity(String city);
}
