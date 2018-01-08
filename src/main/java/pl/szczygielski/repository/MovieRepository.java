package pl.szczygielski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szczygielski.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}