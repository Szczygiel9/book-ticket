package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.service.ReservationService;
import pl.szczygielski.service.SeanceService;

import java.util.List;

@RestController
@RequestMapping("/api/seances")
public class SeanceController {

    private ReservationService reservationService;
    private SeanceService seanceService;

    @Autowired
    public SeanceController(ReservationService reservationService, SeanceService seanceService) {
        this.reservationService = reservationService;
        this.seanceService = seanceService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Seance getSeance(@PathVariable Long id) {
        return seanceService.getOne(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Seance> getSeances() {
        return seanceService.returnAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveSeance(@RequestBody Seance seance) {
        seanceService.saveSeance(seance);
    }

    @GetMapping("/{id}/add")
    public Reservation addSeanceToCinema(@PathVariable Long id) {
        Seance seance = seanceService.getOne(id);
        return reservationService.reservateSeat(seance);
    }

    @GetMapping("/in/{city}")
    public List<Seance> searchSeancesByCity(@PathVariable String city) {
        return seanceService.searchSeancesByCity(city);
    }
}
