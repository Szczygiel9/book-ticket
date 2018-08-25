package pl.szczygielski.mapper.impl;

import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.SeanceDTO;
import pl.szczygielski.mapper.CinemaMapper;
import pl.szczygielski.mapper.ReservationMapper;
import pl.szczygielski.mapper.SeanceMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeanceMapperImpl implements SeanceMapper {

    private CinemaMapper cinemaMapper;

    private ReservationMapper reservationMapper;

    public SeanceMapperImpl(final CinemaMapper cinemaMapper, final ReservationMapper reservationMapper) {
        this.cinemaMapper = cinemaMapper;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public SeanceDTO mapSeanceToSeanceDTO(final Seance seance) {
        final SeanceDTO.SeanceDTOBuilder builder = SeanceDTO.builder()
                .id(seance.getId())
                .type(seance.getType())
                .hour(seance.getHour())
                .day(seance.getDay())
                .freeSeats(seance.getFreeSeats())
                .reservedSeats(seance.getReservedSeats());
        buildCinema(builder, seance);
        buildReservation(builder, seance);
        return builder.build();
    }

    private void buildReservation(final SeanceDTO.SeanceDTOBuilder builder, final Seance seance) {
        if (seance.getReservations() != null) {
            builder.reservations(reservationMapper.mapReservationsToDTO(seance.getReservations()));
        }
    }

    private void buildCinema(final SeanceDTO.SeanceDTOBuilder builder, final Seance seance) {
        if (seance.getCinema() != null) {
            builder.cinema(cinemaMapper.mapCinemaToCinemaDTO(seance.getCinema()));
        }
    }

    @Override
    public List<SeanceDTO> mapSeancesToDTO(final List<Seance> seances) {
        return seances.stream().map(this::mapSeanceToSeanceDTO).collect(Collectors.toList());
    }
}
