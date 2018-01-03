package pl.szczygielski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczygielski.domain.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>{
}
