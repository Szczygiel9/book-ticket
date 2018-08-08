package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Movie getMovie(final @PathVariable Long id) {
        return movieService.getOne(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Movie> getMovies() {
        return movieService.returnAll();
    }

    @GetMapping(path = "/{id}/seances", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Seance> getSeancesOfMovie(final @PathVariable Long id) {
        return movieService.getSeancesOfMovie(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveMovie(final @RequestBody Movie movie) {
        movieService.saveMovie(movie);
    }
}
