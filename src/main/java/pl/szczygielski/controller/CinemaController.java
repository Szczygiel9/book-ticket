package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.service.CinemaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cinemas")
public class CinemaController {

    private CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cinema>> getCinemas(){
        List<Cinema> cinemas = cinemaService.returnAll();
        return ResponseEntity.ok(cinemas);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cinema> getCinema(@PathVariable Long id){
        if (cinemaService.countQuantity() < id){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cinemaService.getOne(id));
        }
    }

    @GetMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getCities(){
        return ResponseEntity.ok(cinemaService.searchCities());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCinema(@RequestBody Cinema cinema){
        cinemaService.saveCinema(cinema);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cinema.getId())
                .toUri();
        return ResponseEntity.created(location).body(cinema);
    }
}
