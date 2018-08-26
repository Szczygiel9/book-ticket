package pl.szczygielski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.MovieDTO;
import pl.szczygielski.dto.SeanceDTO;
import pl.szczygielski.mapper.MovieMapper;
import pl.szczygielski.mapper.SeanceMapper;
import pl.szczygielski.repository.MovieRepository;
import pl.szczygielski.repository.SeanceRepository;
import pl.szczygielski.service.MovieService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    private SeanceRepository seanceRepository;

    private MovieMapper movieMapper;

    private SeanceMapper seanceMapper;

    @Autowired
    public MovieServiceImpl(final MovieRepository movieRepository, final SeanceRepository seanceRepository, final MovieMapper movieMapper, final SeanceMapper seanceMapper) {
        this.movieRepository = movieRepository;
        this.seanceRepository = seanceRepository;
        this.movieMapper = movieMapper;
        this.seanceMapper = seanceMapper;
    }

    @Override
    public Movie saveMovie(final Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public MovieDTO getMovieById(final Long id) {
        final Movie movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return movieMapper.mapMovieToMovieDTO(movie);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        final List<Movie> allMovies = movieRepository.findAll();
        return movieMapper.mapMovieListToMovieListDTO(allMovies);
    }

    @Override
    public Long countQuantity() {
        return movieRepository.count();
    }

    public List<SeanceDTO> getSeancesOfMovie(final Long movieId) {
        final List<Seance> seances = seanceRepository.getSeancesByMovieId(movieId);
        return seanceMapper.mapSeancesToDTO(seances);
    }
}
