package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.service.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/seances")
public class SeanceController {

    private CinemaService cinemaService;
    private ReservationService reservationService;
    private SeanceService seanceService;

    @Autowired
    public SeanceController(CinemaService cinemaService, ReservationService reservationService, SeanceService seanceService) {
        this.cinemaService = cinemaService;
        this.reservationService = reservationService;
        this.seanceService = seanceService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Seance> getSeance(@PathVariable Long id){
        if (id > seanceService.countQuantity()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(seanceService.getOne(id));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Seance>> getSeances(){
        return ResponseEntity.ok(seanceService.returnAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSeance(@RequestBody Seance seance){
        seanceService.saveSeance(seance);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(seance.getId())
                .toUri();
        return ResponseEntity.created(location).body(seance);
    }

    @GetMapping("/{id}/add")
    public ResponseEntity<Reservation> addSeanceToCinema(@PathVariable Long id){
        Seance seance = seanceService.getOne(id);
        Reservation reservation = reservationService.reservateSeat(seance);
        if (reservation == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.ok().body(reservation);
        }
    }

    @GetMapping("/in/{city}")
    public ResponseEntity<List<Seance>> searchSeancesByCity(@PathVariable String city){
        return ResponseEntity.ok(seanceService.searchSeancesByCity(city));
    }
}
