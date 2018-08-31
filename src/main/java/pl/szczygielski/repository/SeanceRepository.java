package pl.szczygielski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczygielski.domain.Seance;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

    Optional<Seance> findById(final Long id);

    List<Seance> getSeancesByMovieId(final Long id);

    List<Seance> getSeancesByCinemaAddressCity(final String city);
}
