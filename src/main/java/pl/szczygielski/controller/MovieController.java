package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.MovieRepository;
import pl.szczygielski.service.MovieService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieRepository movieRepository;
    private MovieService movieService;

    @Autowired
    public MovieController(MovieRepository movieRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    @GetMapping(path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovie(@PathVariable Long id){
        if (id > movieRepository.count()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(movieRepository.findOne(id));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getMovies(){
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @GetMapping(path = "/{id}/seances", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Seance>> getSeancesOfMovie(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getSeancesofMovie(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
        movieRepository.save(movie);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movie.getId())
                .toUri();
        return ResponseEntity.created(location).body(movie);
    }
}
