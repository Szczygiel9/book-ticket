package pl.szczygielski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.szczygielski.domain.Cinema;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>{

    Optional<Cinema> findById(final Long id);

    @Query(value = "select cinemas.city from cinemas, seances where seances.cinema_id = cinemas.id_cinema", nativeQuery = true)
    List<String> getCitiesWhereMoviesArePlayed();
}
