package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.repository.MovieRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieRepository movieRepository;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
