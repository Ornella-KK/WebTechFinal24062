package ticketbooking.TicketBookingApp.DTO;

import ticketbooking.TicketBookingApp.Model.Flights;
import ticketbooking.TicketBookingApp.Model.Passenger;

public class BookingDetails {
        private String bookingId;
        private Passenger passenger;
        private Flights flight;

    public BookingDetails() {
    }

    public BookingDetails(String bookingId, Passenger passenger, Flights flight) {
        this.bookingId = bookingId;
        this.passenger = passenger;
        this.flight = flight;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flights getFlight() {
        return flight;
    }

    public void setFlight(Flights flight) {
        this.flight = flight;
    }
}
