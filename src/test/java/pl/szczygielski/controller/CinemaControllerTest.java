package pl.szczygielski.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.szczygielski.domain.Address;
import pl.szczygielski.dto.CinemaDTO;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CinemaController.class)
public class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CinemaController cinemaController;

    @Test
    public void getAllCinemasTest() throws Exception {
        final CinemaDTO cinema = createCinema();
        given(cinemaController.getCinemas()).willReturn(Arrays.asList(cinema));

        mockMvc.perform(get("/api/cinemas/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Cinema City")))
                .andExpect(jsonPath("$[0].address.city", is("Lublin")))
                .andExpect(jsonPath("$[0].address.number", is("21")))
                .andExpect(jsonPath("$[0].address.street", is("Filaretów")));
    }

    @Test
    public void testGetOne() throws Exception {
        given(cinemaController.getCinema(1L)).willReturn(createCinema());

        mockMvc.perform(get("/api/cinemas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Cinema City")))
                .andExpect(jsonPath("$.address.city", is("Lublin")))
                .andExpect(jsonPath("$.address.number", is("21")))
                .andExpect(jsonPath("$.address.street", is("Filaretów")));
    }

    @Test
    public void getNotExistingOneTest() throws Exception {
        given(cinemaController.getCinema(anyLong())).willThrow(EntityNotFoundException.class);
        mockMvc.perform(get("/api/cinemas/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCitiesTest() throws Exception {
        final List<String> cities = getCities();
        given(cinemaController.getCitesWhereMoviesArePlayed()).willReturn(cities);

        mockMvc.perform(get("/api/cinemas/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]", is(cities.get(0))))
                .andExpect(jsonPath("$[1]", is(cities.get(1))))
                .andExpect(jsonPath("$[2]", is(cities.get(2))))
                .andExpect(jsonPath("$[3]", is(cities.get(3))));
    }

    private List<String> getCities() {
        return Arrays.asList("Lublin", "NY", "Moskwa", "WWa");
    }

    private CinemaDTO createCinema() {
        return CinemaDTO.builder()
                .id(1L)
                .name("Cinema City")
                .address(getAddress())
                .build();
    }

    public static Address getAddress() {
        final Address address = new Address();
        address.setCity("Lublin");
        address.setNumber("21");
        address.setStreet("Filaretów");
        return address;
    }

}