package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.dto.ReservationDTO;
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
    public List<ReservationDTO> getReservations() {
        return reservationService.returnAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationDTO getReservation(final @PathVariable Long id) {
        return reservationService.getOne(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveReservation(final @RequestBody Reservation reservation) {
        reservationService.saveReservation(reservation);
    }
}
