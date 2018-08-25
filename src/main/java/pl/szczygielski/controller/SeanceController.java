package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.SeanceDTO;
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
    public SeanceDTO getSeance(@PathVariable Long id) {
        return seanceService.getOne(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SeanceDTO> getSeances() {
        return seanceService.returnAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveSeance(@RequestBody Seance seance) {
        seanceService.saveSeance(seance);
    }

    @GetMapping("/{seanceId}/add")
    public Reservation addSeanceToCinema(@PathVariable Long seanceId) {
        return reservationService.reservateSeat(seanceId);
    }

    @GetMapping("/in/{city}")
    public List<SeanceDTO> searchSeancesByCity(@PathVariable String city) {
        return seanceService.searchSeancesByCity(city);
    }
}
