package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.SeanceDTO;

import java.util.List;

@Service
public interface SeanceService {
    Seance saveSeance(Seance seance);

    SeanceDTO getOne(Long id);

    List<SeanceDTO> returnAll();
    Long countQuantity();

    List<SeanceDTO> searchSeancesByCity(String city);
}
