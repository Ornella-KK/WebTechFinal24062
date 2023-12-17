package ticketbooking.TicketBookingApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import ticketbooking.TicketBookingApp.Generator.BookingIdGenerator;

import java.time.LocalDate;
@Entity
public class Booking {
    @Id
    private String bookingId;
    private LocalDate bookingTime;
    @Column(name = "flight_id")
    private String flightId;
    @Column(columnDefinition = "VARCHAR(255) default 'active'")
    private String status;

    public Booking() {
        this.bookingId = BookingIdGenerator.generateBookingId();
    }

    public Booking(LocalDate bookingTime, String flightId, String status) {
        this.bookingId = BookingIdGenerator.generateBookingId();
        this.bookingTime = bookingTime;
        this.flightId = flightId;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public LocalDate getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDate bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
