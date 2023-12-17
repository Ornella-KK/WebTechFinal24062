package ticketbooking.TicketBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ticketbooking.TicketBookingApp.Model.Booking;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, String> {
    @Query("SELECT b, f, p FROM Booking b JOIN Passenger p ON b.bookingId = p.bookingId JOIN Flights f ON b.flightId = f.flightId WHERE p.userId = :userId")
    List<Object[]> findBookingDetailsByUserId(@Param("userId") Integer userId);

}
