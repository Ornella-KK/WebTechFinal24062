package ticketbooking.TicketBookingApp.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticketbooking.TicketBookingApp.DTO.BookingDetails;
import ticketbooking.TicketBookingApp.Model.Booking;
import ticketbooking.TicketBookingApp.Model.Flights;
import ticketbooking.TicketBookingApp.Model.Passenger;
import ticketbooking.TicketBookingApp.Repository.BookingRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepo bookRepo;
    public String saveBook(Booking booking){
        if (booking != null) {
            if (bookingAlreadyExists(booking.getBookingId())) {
                logger.info("Booking already exists");
                return "Booking already exists";
            } else {
                bookRepo.save(booking);
                logger.info("Booking saved successfully");
                return "Booking saved successfully";
            }
        } else {
            return null;
        }
    }
    public boolean bookingAlreadyExists(String bookingId) {
        return bookRepo.existsById(bookingId);
    }

    public List<Object[]> getBookingDetailsByFlightId(Integer userId) {
        return bookRepo.findBookingDetailsByUserId(userId);
    }

}
