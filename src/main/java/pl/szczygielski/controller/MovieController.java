package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.service.MovieService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/{id}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Movie> getMovie(@PathVariable Long id){
        if (id > movieService.countQuantity()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(movieService.getOne(id));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Movie>> getMovies(){
        return ResponseEntity.ok(movieService.returnAll());
    }

    @GetMapping(path = "/{id}/seances", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Seance>> getSeancesOfMovie(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getSeancesOfMovie(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie){
        movieService.saveMovie(movie);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(movie.getId())
                .toUri();
        return ResponseEntity.created(location).body(movie);
    }
}
