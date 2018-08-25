package pl.szczygielski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.ReservationDTO;
import pl.szczygielski.mapper.ReservationMapper;
import pl.szczygielski.repository.ReservationRepository;
import pl.szczygielski.repository.SeanceRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;

@Component
public class ReservationServiceImpl implements ReservationService{

    private SeanceRepository seanceRepository;
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    @Autowired
    public ReservationServiceImpl(SeanceRepository seanceRepository, ReservationRepository reservationRepository, final ReservationMapper reservationMapper) {
        this.seanceRepository = seanceRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationDTO getOne(Long id) {
        final Reservation reservation = reservationRepository.findOne(id);
        if (reservation == null) {
            throw new EntityNotFoundException();
        }
        return reservationMapper.mapReservationToReservationDTO(reservation);
    }

    @Override
    public List<ReservationDTO> returnAll() {
        final List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.mapReservationsToDTO(reservations);
    }

    @Override
    public Long countQuantity() {
        return reservationRepository.count();
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation reservateSeat(Long seanceId) {

        final Seance seance = seanceRepository.getOne(seanceId);
        if(seance.getFreeSeats() > 0) {
            seance.setFreeSeats(seance.getFreeSeats() - 1);
            seance.setReservedSeats(seance.getReservedSeats() + 1);
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
