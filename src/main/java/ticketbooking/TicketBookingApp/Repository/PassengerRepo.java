package ticketbooking.TicketBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ticketbooking.TicketBookingApp.Model.Passenger;
@Repository
public interface PassengerRepo extends JpaRepository<Passenger, String> {
}
