package pl.szczygielski.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.szczygielski.domain.Movie;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieController movieController;

    @Test
    public void getAllMoviesTest() throws Exception {
        given(movieController.getMovies()).willReturn(Arrays.asList(getMovie()));

        mockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Titanic")))
                .andExpect(jsonPath("$[0].director", is("Cameron")))
                .andExpect(jsonPath("$[0].filmGenre", is("Comedy")));
    }

    @Test
    public void getOneTest() throws Exception {
        given(movieController.getMovie(1L)).willReturn(getMovie());

        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Titanic")))
                .andExpect(jsonPath("$.director", is("Cameron")))
                .andExpect(jsonPath("$.filmGenre", is("Comedy")));
    }

    @Test
    public void getNonExistingOneTest() throws Exception {
        given(movieController.getMovie(anyLong())).willThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/api/movies/1"))
                .andExpect(status().isNotFound());
    }

    private Movie getMovie() {
        final Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Titanic");
        movie.setDirector("Cameron");
        movie.setFilmGenre("Comedy");
        return movie;
    }
}