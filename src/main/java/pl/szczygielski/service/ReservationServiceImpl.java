package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.repository.ReservationRepository;
import pl.szczygielski.repository.SeanceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;

@Component
public class ReservationServiceImpl implements ReservationService{

    private SeanceRepository seanceRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(SeanceRepository seanceRepository, ReservationRepository reservationRepository) {
        this.seanceRepository = seanceRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation getOne(Long id) {
        final Reservation reservation = reservationRepository.findOne(id);
        if (reservation == null) {
            throw new EntityNotFoundException();
        }
        return reservation;
    }

    @Override
    public List<Reservation> returnAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Long countQuantity() {
        return reservationRepository.count();
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation reservateSeat(Seance seance){
        if(seance.getFreeSeats() > 0) {
            seance.setFreeSeats(seance.getFreeSeats() - 1);
            seance.setReservatedSeats(seance.getReservatedSeats() + 1);
            Reservation reservation = new Reservation();
            Random random = new Random();
            reservation.setName(random.nextInt(1000000));
            reservation.setSeance(seance);
            reservationRepository.save(reservation);
            seanceRepository.save(seance);
            return reservation;
        }
        else {
            return null;
        }
    }
}
