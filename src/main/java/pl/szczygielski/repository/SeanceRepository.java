package pl.szczygielski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczygielski.domain.Seance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
}
