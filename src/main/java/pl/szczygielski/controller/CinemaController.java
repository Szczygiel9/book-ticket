package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.repository.CinemaRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaController {

    private CinemaRepository cinemaRepository;

    @Autowired
    public CinemaController(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cinema>> getCinemas(){
        List<Cinema> cinemas = cinemaRepository.findAll();
        return ResponseEntity.ok(cinemas);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cinema> getCinema(@PathVariable Long id){
        if (cinemaRepository.count() < id){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cinemaRepository.findOne(id));
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCinema(@RequestBody Cinema cinema){
        cinemaRepository.save(cinema);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cinema.getId())
                .toUri();
        return ResponseEntity.created(location).body(cinema);
    }
}
