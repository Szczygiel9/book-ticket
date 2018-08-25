package pl.szczygielski.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MovieDTO {

    private Long id;
    private String title;
    private String director;
    private String filmGenre;
    private List<SeanceDTO> seances;
}
