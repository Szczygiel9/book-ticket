package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.service.CinemaService;

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
    public List<Cinema> getCinemas() {
        return cinemaService.returnAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cinema getCinema(final @PathVariable Long id) {
        return cinemaService.getOne(id);
    }

    @GetMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getCities() {
        return cinemaService.searchCities();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCinema(final @RequestBody Cinema cinema) {
        cinemaService.saveCinema(cinema);
    }
}
