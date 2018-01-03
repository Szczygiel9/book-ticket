package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.repository.ReservationRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id){
        if(id > reservationRepository.count()){
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(reservationRepository.findOne(id));
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveReservation(@RequestBody Reservation reservation){
        reservationRepository.save(reservation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reservation.getId())
                .toUri();
        return ResponseEntity.created(location).body(reservation);
    }
}
