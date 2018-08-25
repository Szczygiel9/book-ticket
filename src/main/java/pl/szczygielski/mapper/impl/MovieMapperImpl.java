package pl.szczygielski.mapper.impl;

import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.dto.MovieDTO;
import pl.szczygielski.mapper.MovieMapper;
import pl.szczygielski.mapper.SeanceMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapperImpl implements MovieMapper {

    private SeanceMapper seanceMapper;

    public MovieMapperImpl(final SeanceMapper seanceMapper) {
        this.seanceMapper = seanceMapper;
    }

    @Override
    public MovieDTO mapMovieToMovieDTO(final Movie movie) {
        return MovieDTO.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .filmGenre(movie.getFilmGenre())
                .seances(seanceMapper.mapSeancesToDTO(movie.getSeances()))
                .build();
    }

    @Override
    public List<MovieDTO> mapMovieListToMovieListDTO(final List<Movie> movies) {
        return movies.stream().map(this::mapMovieToMovieDTO).collect(Collectors.toList());
    }
}
