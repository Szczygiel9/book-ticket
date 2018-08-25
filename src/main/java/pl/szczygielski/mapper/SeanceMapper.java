package pl.szczygielski.mapper;

import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.SeanceDTO;

import java.util.List;

public interface SeanceMapper {

    SeanceDTO mapSeanceToSeanceDTO(final Seance seance);

    List<SeanceDTO> mapSeancesToDTO(final List<Seance> seances);
}
