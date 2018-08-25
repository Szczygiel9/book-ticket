package pl.szczygielski.mapper;

import org.junit.Before;
import org.junit.Test;
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
        assertEquals("Lipowa 8", cinemaDTO.getAddress());
        assertEquals("Lublin", cinemaDTO.getCity());
    }

    @Test
    public void mapCinemasToDto() {
        final List<CinemaDTO> cinemaDTOS = cinemaMapper.mapCinemasToCinemasListDTO(Arrays.asList(getCinema()));
        assertEquals(1, cinemaDTOS.size());
        testCinema(cinemaDTOS.get(0));
    }

    public static Cinema getCinema() {
        final Cinema cinema = new Cinema();
        cinema.setId(44L);
        cinema.setAddress("Lipowa 8");
        cinema.setCity("Lublin");
        return cinema;
    }
}