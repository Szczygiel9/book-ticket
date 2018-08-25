package pl.szczygielski.mapper;

import org.junit.Before;
import org.junit.Test;
import pl.szczygielski.domain.Movie;
import pl.szczygielski.dto.MovieDTO;
import pl.szczygielski.mapper.impl.CinemaMapperImpl;
import pl.szczygielski.mapper.impl.MovieMapperImpl;
import pl.szczygielski.mapper.impl.ReservationMapperImpl;
import pl.szczygielski.mapper.impl.SeanceMapperImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MovieMapperTest {

    private MovieMapperImpl movieMapper;

    @Before
    public void setUp() throws Exception {
        final SeanceMapper seanceMapper = new SeanceMapperImpl(new CinemaMapperImpl(), new ReservationMapperImpl());
        movieMapper = new MovieMapperImpl(seanceMapper);
    }

    @Test
    public void mapMovieToDTOTest() {
        final MovieDTO movieDTO = movieMapper.mapMovieToMovieDTO(getMovie());

        testMovie(movieDTO);
    }

    private void testMovie(final MovieDTO movieDTO) {
        final Movie movie = getMovie();
        assertEquals(movie.getId(), movieDTO.getId());
        assertEquals(movie.getFilmGenre(), movieDTO.getFilmGenre());
        assertEquals(movie.getDirector(), movieDTO.getDirector());
        assertEquals(movie.getTitle(), movieDTO.getTitle());
        assertFalse(movie.getSeances().isEmpty());
    }

    @Test
    public void mapMoviesListToDTO() {
        final List<MovieDTO> movieDTOS = movieMapper.mapMovieListToMovieListDTO(Arrays.asList(getMovie()));
        assertTrue(movieDTOS.size() == 1);
        testMovie(movieDTOS.get(0));
    }

    public static Movie getMovie() {
        final Movie movie = new Movie();
        movie.setId(13L);
        movie.setFilmGenre("comedy");
        movie.setDirector("Stan Lee");
        movie.setTitle("Avengers 2");
        movie.setSeances(Arrays.asList(SeanceMapperTest.getSeance()));
        return movie;
    }


}