package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.MovieRepository;

import java.util.List;

@Component
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getOne(Long id) {
        return movieRepository.findOne(id);
    }

    @Override
    public List<Movie> returnAll() {
        return movieRepository.findAll();
    }

    @Override
    public Long countQuantity() {
        return movieRepository.count();
    }

    public List<Seance> getSeancesOfMovie(Long movieId){
        Movie movie = movieRepository.findOne(movieId);
        return movie.getSeances();
    }
}
