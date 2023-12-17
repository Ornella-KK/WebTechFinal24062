package ticketbooking.TicketBookingApp.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ticketbooking.TicketBookingApp.DTO.BookingDetails;
import ticketbooking.TicketBookingApp.DTO.BookingRequest;
import ticketbooking.TicketBookingApp.Model.Booking;
import ticketbooking.TicketBookingApp.Model.Flights;
import ticketbooking.TicketBookingApp.Model.Passenger;
import ticketbooking.TicketBookingApp.Service.BookingService;
import ticketbooking.TicketBookingApp.Service.FlightService;
import ticketbooking.TicketBookingApp.Service.PassengerService;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/Auth/Book")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private FlightService flightService;
    @GetMapping("/showBookingForms/{flightId}")
    public String showUserBookingForm(@PathVariable String flightId, Model model) {
        model.addAttribute("flightId", flightId);
        model.addAttribute("bookings", new BookingRequest()); // Add the booking object if needed
        return "UserBookForm";
    }

    @PostMapping("/bookFlight/{flightId}")
    public String bookFlightUser(@PathVariable String flightId,
                                 @ModelAttribute("bookings") BookingRequest passengerRequest,
                                 RedirectAttributes redirectAttributes, HttpServletRequest request) {

        // Step 0: Retrieve user ID from cookie
        Cookie[] cookies = request.getCookies();
        int userId = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = Integer.parseInt(cookie.getValue());
                    logger.info("User Id: {}", userId);
                    break;
                }
            }
        }

        // Step 1: Retrieve the flight by ID
        Flights flight = flightService.getFlightById(flightId);

        // Check if there are available seats
        if (flight.getAvailableSeats() > 0) {
            // Step 2: Create and save Booking with the associated flightId
            Booking booking = new Booking(LocalDate.now(),flightId,"Active");
            bookingService.saveBook(booking);

            // Step 3: Create Passenger and associate it with the booking and user ID
            Passenger passenger = new Passenger(
                    passengerRequest.getName(),
                    passengerRequest.getDateOfBirth(),
                    passengerRequest.getPassportId(),
                    passengerRequest.getGender(),
                    passengerRequest.getContactInfo(),
                    booking.getBookingId(),
                    userId
            );

            // Step 4: Save Passenger
            passengerService.savePassenger(passenger);

            // Step 5: Decrease available seats in the flight
            flight.setAvailableSeats(flight.getAvailableSeats() - 1);
            flightService.updateAvailableSeats(flightId, flight.getAvailableSeats());

            return "redirect:/Auth/Book/listBook";
        } else {
            // No available seats, redirect with a message
            redirectAttributes.addFlashAttribute("noSeats", true);
            return "redirect:/UserBookForm";
        }
    }

    @GetMapping("/listBook")
    public String viewBookings(@CookieValue(name = "userId", defaultValue = "0") Integer userId, Model model) {
        List<Object[]> bookingDetails = bookingService.getBookingDetailsByFlightId(userId);
        model.addAttribute("bookingDetails", bookingDetails);
        // Debug or print the content of bookingDetails
        System.out.println("Booking Details: " + bookingDetails);
        return "UserBookings";
    }



}
