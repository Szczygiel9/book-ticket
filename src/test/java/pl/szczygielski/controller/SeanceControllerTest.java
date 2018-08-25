package pl.szczygielski.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.szczygielski.dto.SeanceDTO;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SeanceController.class)
public class SeanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeanceController seanceController;

    @Test
    public void getAllSeancesTest() throws Exception {
        given(seanceController.getSeances()).willReturn(Arrays.asList(getSeance()));

        mockMvc.perform(get("/api/seances"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].freeSeats", is(30)))
                .andExpect(jsonPath("$[0].reservedSeats", is(15)))
                .andExpect(jsonPath("$[0].day", is("01")))
                .andExpect(jsonPath("$[0].hour", is("18:30")))
                .andExpect(jsonPath("$[0].type", is("2D")));
    }

    @Test
    public void getOneSeanceTest() throws Exception {
        given(seanceController.getSeance(1L)).willReturn(getSeance());

        mockMvc.perform(get("/api/seances/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.freeSeats", is(30)))
                .andExpect(jsonPath("$.reservedSeats", is(15)))
                .andExpect(jsonPath("$.day", is("01")))
                .andExpect(jsonPath("$.hour", is("18:30")))
                .andExpect(jsonPath("$.type", is("2D")));
    }

    @Test
    public void getNonExistingOneTest() throws Exception {
        given(seanceController.getSeance(anyLong())).willThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/api/seances/1"))
                .andExpect(status().isNotFound());
    }

    private SeanceDTO getSeance() {
        return SeanceDTO.builder()
                .id(1L)
                .freeSeats(30)
                .reservedSeats(15)
                .day("01")
                .hour("18:30")
                .type("2D")
                .build();
    }
}