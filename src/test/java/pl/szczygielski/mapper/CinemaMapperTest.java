package pl.szczygielski.mapper;

import org.junit.Before;
import org.junit.Test;
import pl.szczygielski.controller.CinemaControllerTest;
import pl.szczygielski.domain.Address;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.dto.CinemaDTO;
import pl.szczygielski.mapper.impl.CinemaMapperImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CinemaMapperTest {

    private CinemaMapper cinemaMapper;

    @Before
    public void setUp() throws Exception {
        cinemaMapper = new CinemaMapperImpl();
    }

    @Test
    public void mapCinemaTest() {
        final CinemaDTO cinemaDTO = cinemaMapper.mapCinemaToCinemaDTO(getCinema());

        testCinema(cinemaDTO);
    }

    private void testCinema(final CinemaDTO cinemaDTO) {
        assertEquals(Long.valueOf(44), cinemaDTO.getId());
        assertEquals("Cinema City", cinemaDTO.getName());
        final Address expectedAddress = CinemaControllerTest.getAddress();
        final Address actualAddress = cinemaDTO.getAddress();
        assertEquals(expectedAddress.getCity(), actualAddress.getCity());
        assertEquals(expectedAddress.getNumber(), actualAddress.getNumber());
        assertEquals(expectedAddress.getStreet(), actualAddress.getStreet());
    }

    @Test
    public void mapCinemasToDto() {
        final List<CinemaDTO> cinemaDTOS = cinemaMapper.mapCinemasToCinemasListDTO(Arrays.asList(getCinema()));
        assertEquals(1, cinemaDTOS.size());
        testCinema(cinemaDTOS.get(0));
    }

    public static Cinema getCinema() {
        final Cinema cinema = new Cinema();
        cinema.setName("Cinema City");
        cinema.setId(44L);
        cinema.setAddress(CinemaControllerTest.getAddress());
        return cinema;
    }

}