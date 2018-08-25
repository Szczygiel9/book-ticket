package pl.szczygielski.mapper;

import org.junit.Before;
import org.junit.Test;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.SeanceDTO;
import pl.szczygielski.mapper.impl.CinemaMapperImpl;
import pl.szczygielski.mapper.impl.ReservationMapperImpl;
import pl.szczygielski.mapper.impl.SeanceMapperImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SeanceMapperTest {

    private SeanceMapperImpl seanceMapper;

    @Before
    public void setUp() throws Exception {
        final CinemaMapperImpl cinemaMapper = new CinemaMapperImpl();
        final ReservationMapper reservationMapper = new ReservationMapperImpl();
        seanceMapper = new SeanceMapperImpl(cinemaMapper, reservationMapper);
    }

    @Test
    public void mapSeanceToSeanceDTO() {
        final Seance seance = getSeance();
        final List<SeanceDTO> seanceDTOS = seanceMapper.mapSeancesToDTO(Arrays.asList(seance));

        assertEquals(1, seanceDTOS.size());

        final SeanceDTO seanceDTO = seanceDTOS.get(0);
        assertEquals(seance.getId(), seanceDTO.getId());
        assertEquals(seance.getType(), seanceDTO.getType());
        assertEquals(seance.getHour(), seanceDTO.getHour());
        assertEquals(seance.getDay(), seanceDTO.getDay());
        assertEquals(seance.getFreeSeats(), seanceDTO.getFreeSeats());
        assertEquals(seance.getReservedSeats(), seanceDTO.getReservedSeats());

        assertTrue(seanceDTO.getCinema() != null);
        assertTrue(seanceDTO.getReservations() != null);
    }

    public static Seance getSeance() {
        final Seance seance = new Seance();
        seance.setId(2L);
        seance.setType("2D");
        seance.setHour("14");
        seance.setDay("23");
        seance.setFreeSeats(30);
        seance.setReservedSeats(11);
        seance.setCinema(CinemaMapperTest.getCinema());
        seance.setReservations(ReservationMapperTest.getReservationsList());
        return seance;
    }
}