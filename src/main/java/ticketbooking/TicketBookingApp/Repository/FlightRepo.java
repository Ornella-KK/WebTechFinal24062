package ticketbooking.TicketBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.TicketBookingApp.Model.Flights;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flights, String> {
    List<Flights> findByOriginAndDestinationAndFlightDate(String origin, String destination, LocalDate flightDate);
    List<Flights> findByDestination(String destination);
}
