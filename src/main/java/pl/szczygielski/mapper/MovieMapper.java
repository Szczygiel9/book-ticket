package pl.szczygielski.mapper;

import pl.szczygielski.domain.Movie;
import pl.szczygielski.dto.MovieDTO;

import java.util.List;

public interface MovieMapper {

    MovieDTO mapMovieToMovieDTO(final Movie movie);

    List<MovieDTO> mapMovieListToMovieListDTO(final List<Movie> movies);
}
