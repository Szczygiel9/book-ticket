package pl.szczygielski.controller;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.szczygielski.domain.SeanceType;
import pl.szczygielski.dto.SeanceDTO;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
                .andExpect(jsonPath("$[0].type", is("_2D")))
                .andExpect(jsonPath("$[0].seanceDate", is("2018-03-01 18:30")))
                .andExpect(jsonPath("$[0].freeSeats", is(30)))
                .andExpect(jsonPath("$[0].reservedSeats", is(15)));
    }

    @Test
    public void getOneSeanceTest() throws Exception {
        final SeanceDTO seance = getSeance();
        given(seanceController.getSeance(1L)).willReturn(seance);

        mockMvc.perform(get("/api/seances/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.type", is("_2D")))
                .andExpect(jsonPath("$.seanceDate", is("2018-03-01 18:30")))
                .andExpect(jsonPath("$.freeSeats", is(30)))
                .andExpect(jsonPath("$.reservedSeats", is(15)));
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
                .seanceDate(getDate())
                .type(SeanceType._2D)
                .build();
    }

    @SneakyThrows
    private Date getDate() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.parse("2018-03-01 18:30");
    }
}