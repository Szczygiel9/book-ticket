package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Cinema;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.CinemaRepository;
import pl.szczygielski.repository.SeanceRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CinemaServiceImpl implements CinemaService {

    private SeanceRepository seanceRepository;
    private CinemaRepository cinemaRepository;

    @Autowired
    public CinemaServiceImpl(SeanceRepository seanceRepository, CinemaRepository cinemaRepository) {
        this.seanceRepository = seanceRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public Cinema saveCinema(Cinema cinema){
        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema getOne(Long id) {
        return cinemaRepository.findOne(id);
    }

    @Override
    public List<Cinema> returnAll() {
        return cinemaRepository.findAll();
    }

    @Override
    public Long countQuantity() {
        return cinemaRepository.count();
    }

    public void addSeanceToCinema(Long cinemaId, Long seanceId){
        Cinema cinema = cinemaRepository.findById(cinemaId);
        Seance seance = seanceRepository.findById(seanceId);

        cinema.getSeances().add(seance);
        cinemaRepository.save(cinema);
    }

    public List<String> searchCities(){
        List<Seance> seances =  seanceRepository.findAll();
        List<String > cities = new ArrayList<>();
        seances.forEach(seance ->{
            String city = seance.getCinema().getCity();
            if (!cities.contains(city)){
                cities.add(city);
            }
        } );
        return cities;
    }
}
