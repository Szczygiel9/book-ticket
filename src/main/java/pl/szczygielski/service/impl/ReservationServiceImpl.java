package pl.szczygielski.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.domain.Seance;
import pl.szczygielski.dto.ReservationDTO;
import pl.szczygielski.exception.SeanceIsFullException;
import pl.szczygielski.mapper.ReservationMapper;
import pl.szczygielski.repository.ReservationRepository;
import pl.szczygielski.repository.SeanceRepository;
import pl.szczygielski.service.ReservationService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;

@Component
public class ReservationServiceImpl implements ReservationService {

    private SeanceRepository seanceRepository;
    private ReservationRepository reservationRepository;
    private ReservationMapper reservationMapper;

    @Autowired
    public ReservationServiceImpl(final SeanceRepository seanceRepository, final ReservationRepository reservationRepository, final ReservationMapper reservationMapper) {
        this.seanceRepository = seanceRepository;
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationDTO getReservationById(final Long id) {
        final Reservation reservation = reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return reservationMapper.mapReservationToReservationDTO(reservation);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        final List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.mapReservationsToDTO(reservations);
    }

    @Override
    public Long countQuantity() {
        return reservationRepository.count();
    }

    @Override
    public Reservation saveReservation(final Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation reservateSeat(final Long seanceId) {
        final Seance seance = seanceRepository.getOne(seanceId);
        if (reservationCanBeMade(seance)) {
            changeFreeAndReservedSeats(seance);
            Reservation reservation = getNewReservation(seance);
            reservationRepository.save(reservation);
            seanceRepository.save(seance);
            return reservation;
        } else {
            throw new SeanceIsFullException("there is no free seats for this seance");
        }
    }

    private boolean reservationCanBeMade(final Seance seance) {
        return seance.getFreeSeats() > 0;
    }

    private void changeFreeAndReservedSeats(final Seance seance) {
        changeFreeSeats(seance);
        changeReservedSeats(seance);
    }

    private void changeFreeSeats(final Seance seance) {
        final int newFreeSeatsCount = seance.getFreeSeats() - 1;
        seance.setFreeSeats(newFreeSeatsCount);
    }

    private void changeReservedSeats(final Seance seance) {
        final int newReservedSeatsCount = seance.getReservedSeats() + 1;
        seance.setReservedSeats(newReservedSeatsCount);
    }

    private Reservation getNewReservation(final Seance seance) {
        Reservation reservation = new Reservation();
        Random random = new Random();
        reservation.setName(random.nextInt(1000000));
        reservation.setSeance(seance);
        return reservation;
    }
}
