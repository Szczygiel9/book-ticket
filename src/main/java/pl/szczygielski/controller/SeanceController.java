package pl.szczygielski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.SeanceRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/seances")
public class SeanceController {

    private SeanceRepository seanceRepository;

    @Autowired
    public SeanceController(SeanceRepository seanceRepository) {
        this.seanceRepository = seanceRepository;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Seance> getSeance(@PathVariable Long id){
        if (id > seanceRepository.count()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(seanceRepository.findOne(id));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Seance>> getSeances(){
        return ResponseEntity.ok(seanceRepository.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveSeance(@RequestBody Seance seance){
        seanceRepository.save(seance);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(seance.getId())
                .toUri();
        return ResponseEntity.created(location).body(seance);
    }
}
