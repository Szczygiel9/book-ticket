package pl.szczygielski.mapper.impl;

import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.dto.ReservationDTO;
import pl.szczygielski.mapper.ReservationMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public List<ReservationDTO> mapReservationsToDTO(final List<Reservation> reservations) {
        return reservations.stream().map(this::mapReservationToReservationDTO).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO mapReservationToReservationDTO(final Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .reservationCode(reservation.getReservationCode())
                .build();
    }
}
