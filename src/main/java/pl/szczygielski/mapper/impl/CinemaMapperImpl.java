package pl.szczygielski.mapper.impl;

import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.dto.CinemaDTO;
import pl.szczygielski.mapper.CinemaMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CinemaMapperImpl implements CinemaMapper {

    @Override
    public List<CinemaDTO> mapCinemasToCinemasListDTO(final List<Cinema> cinemas) {
        return cinemas.stream().map(this::mapCinemaToCinemaDTO).collect(Collectors.toList());
    }

    @Override
    public CinemaDTO mapCinemaToCinemaDTO(final Cinema cinema) {
        return CinemaDTO.builder()
                .id(cinema.getId())
                .name(cinema.getName())
                .address(cinema.getAddress())
                .build();
    }
}
