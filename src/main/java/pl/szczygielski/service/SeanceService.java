package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.SeanceDTO;

import java.util.List;

@Service
public interface SeanceService {
    Seance saveSeance(Seance seance);

    SeanceDTO getSeanceById(Long id);

    List<SeanceDTO> getAllSeances();
    Long countQuantity();

    List<SeanceDTO> getSeancesInCity(String city);
}
