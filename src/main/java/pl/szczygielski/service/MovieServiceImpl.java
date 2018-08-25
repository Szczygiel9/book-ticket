package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.dto.MovieDTO;
import pl.szczygielski.dto.SeanceDTO;
import pl.szczygielski.mapper.MovieMapper;
import pl.szczygielski.mapper.SeanceMapper;
import pl.szczygielski.repository.MovieRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    private MovieMapper movieMapper;

    private SeanceMapper seanceMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, final MovieMapper movieMapper, final SeanceMapper seanceMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.seanceMapper = seanceMapper;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public MovieDTO getOne(Long id) {
        final Movie movie = movieRepository.findOne(id);
        if (movie == null) {
            throw new EntityNotFoundException();
        }
        return movieMapper.mapMovieToMovieDTO(movie);
    }

    @Override
    public List<MovieDTO> returnAll() {
        final List<Movie> allMovies = movieRepository.findAll();
        return movieMapper.mapMovieListToMovieListDTO(allMovies);
    }

    @Override
    public Long countQuantity() {
        return movieRepository.count();
    }

    public List<SeanceDTO> getSeancesOfMovie(Long movieId) {
        Movie movie = movieRepository.findOne(movieId);
        return seanceMapper.mapSeancesToDTO(movie.getSeances());
    }
}
