package pl.szczygielski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.domain.Seance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
    Seance findById(Long id);
}
