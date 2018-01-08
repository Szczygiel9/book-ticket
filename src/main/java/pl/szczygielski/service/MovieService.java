package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Seance> getSeancesofMovie(Long movieId){
        Movie movie = movieRepository.findOne(movieId);
        return movie.getSeances();
    }
}
