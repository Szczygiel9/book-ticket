package pl.szczygielski.mapper;

import org.junit.Before;
import org.junit.Test;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.dto.ReservationDTO;
import pl.szczygielski.mapper.impl.ReservationMapperImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReservationMapperTest {

    private ReservationMapper reservationMapper;

    @Before
    public void setUp() throws Exception {
        reservationMapper = new ReservationMapperImpl();
    }

    @Test
    public void mapReservationToDTOTest() {
        final ReservationDTO reservationDTO = reservationMapper.mapReservationToReservationDTO(getReservation());

        assertEquals(Long.valueOf(23), reservationDTO.getId());
        assertEquals(Integer.valueOf(43), reservationDTO.getName());
    }

    @Test
    public void mapReservationListToDTOTest() {
        final List<ReservationDTO> reservationsDTO = reservationMapper.mapReservationsToDTO(getReservationsList());

        assertEquals(3, reservationsDTO.size());
        final ReservationDTO reservationDTO1 = reservationsDTO.get(0);
        final ReservationDTO reservationDTO2 = reservationsDTO.get(1);
        final ReservationDTO reservationDTO3 = reservationsDTO.get(2);

        assertEquals(Long.valueOf(1), reservationDTO1.getId());
        assertEquals(Long.valueOf(2), reservationDTO2.getId());
        assertEquals(Long.valueOf(3), reservationDTO3.getId());

        assertEquals(Integer.valueOf(11), reservationDTO1.getName());
        assertEquals(Integer.valueOf(22), reservationDTO2.getName());
        assertEquals(Integer.valueOf(33), reservationDTO3.getName());
    }

    private Reservation getReservation() {
        final Reservation reservation = new Reservation();
        reservation.setId(23L);
        reservation.setName(43);
        return reservation;
    }

    public static List<Reservation> getReservationsList() {
        final Reservation reservation1 = new Reservation();
        reservation1.setId(1L);
        reservation1.setName(11);
        final Reservation reservation2 = new Reservation();
        reservation2.setId(2L);
        reservation2.setName(22);
        final Reservation reservation3 = new Reservation();
        reservation3.setId(3L);
        reservation3.setName(33);

        return Arrays.asList(reservation1, reservation2, reservation3);
    }
}