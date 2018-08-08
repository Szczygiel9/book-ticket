package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> getReservations() {
        return reservationService.returnAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation getReservation(final @PathVariable Long id) {
        return reservationService.getOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveReservation(final @RequestBody Reservation reservation) {
        reservationService.saveReservation(reservation);
    }
}
