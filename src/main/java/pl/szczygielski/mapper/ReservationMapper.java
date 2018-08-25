package pl.szczygielski.mapper;

import pl.szczygielski.domain.Reservation;
import pl.szczygielski.dto.ReservationDTO;

import java.util.List;

public interface ReservationMapper {

    List<ReservationDTO> mapReservationsToDTO(final List<Reservation> reservations);

    ReservationDTO mapReservationToReservationDTO(final Reservation reservation);
}
