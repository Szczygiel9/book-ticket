package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.domain.Seance;

import java.util.List;

@Service
public interface MovieService {
    Movie saveMovie(Movie movie);
    Movie getOne(Long id);
    List<Movie> returnAll();
    Long countQuantity ();
    List<Seance> getSeancesOfMovie(Long movieId);
}
