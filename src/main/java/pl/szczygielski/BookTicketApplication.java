package pl.szczygielski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.szczygielski.domain.Reservation;
import pl.szczygielski.repository.ReservationRepository;

@SpringBootApplication
public class BookTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookTicketApplication.class, args);
    }
}
