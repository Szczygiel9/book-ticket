package pl.szczygielski.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.szczygielski.dto.ReservationDTO;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationController reservationController;

    @Test
    public void getAllReservationsTest() throws Exception {
        given(reservationController.getReservations()).willReturn(Arrays.asList(getReservation()));

        mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(20)));
    }

    @Test
    public void getOneTest() throws Exception {
        given(reservationController.getReservation(1L)).willReturn(getReservation());

        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(20)));
    }

    @Test
    public void getNonExistingOneTest() throws Exception {
        given(reservationController.getReservation(anyLong())).willThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/api/reservations/1"))
                .andExpect(status().isNotFound());
    }

    private ReservationDTO getReservation() {
        return ReservationDTO.builder()
                .id(1L)
                .name(20)
                .build();
    }
}