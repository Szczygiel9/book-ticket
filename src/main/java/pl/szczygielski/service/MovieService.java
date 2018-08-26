package pl.szczygielski.service;

import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.dto.MovieDTO;
import pl.szczygielski.dto.SeanceDTO;

import java.util.List;

@Service
public interface MovieService {
    Movie saveMovie(Movie movie);

    MovieDTO getMovieById(Long id);

    List<MovieDTO> getAllMovies();
    Long countQuantity ();

    List<SeanceDTO> getSeancesOfMovie(Long movieId);
}
